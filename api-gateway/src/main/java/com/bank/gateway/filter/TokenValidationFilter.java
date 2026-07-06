package com.bank.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Global filter that validates the web-creden token on every request.
 * Login path is whitelisted so unauthenticated requests can pass through.
 * Validation is delegated to identity-service — bank-core stays clean.
 *
 * Flow:
 *   bank-view → gateway (this filter) → identity-service /validate → bank-core (clean)
 */
@Component
public class TokenValidationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(TokenValidationFilter.class);

    private static final String WEB_CREDEN_HEADER = "web-creden";

    @Value("${gateway.auth.identity-service-url:http://localhost:8086}")
    private String identityServiceUrl;

    @Value("#{'${gateway.auth.whitelist:/bank-core/login,/actuator,/fallback}'.split(',')}")
    private List<String> whitelist;

    private final WebClient.Builder webClientBuilder;

    public TokenValidationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // Skip validation for whitelisted paths (login, actuator, fallback)
        if (isWhitelisted(path)) {
            log.debug("Skipping token validation for whitelisted path: {}", path);
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(WEB_CREDEN_HEADER);

        // No token present — reject immediately
        if (token == null || token.isBlank()) {
            log.warn("Missing web-creden token for path: {}", path);
            return reject(exchange, "Missing authentication token");
        }

        // Delegate token validation to identity-service
        return webClientBuilder.build()
                .get()
                .uri(identityServiceUrl + "/auth/validate")
                .header(WEB_CREDEN_HEADER, token)
                .retrieve()
                .toBodilessEntity()
                .flatMap(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        log.debug("Token valid for path: {}", path);
                        return chain.filter(exchange);
                    } else {
                        log.warn("Token invalid for path: {}", path);
                        return reject(exchange, "Invalid or expired token");
                    }
                })
                .onErrorResume(ex -> {
                    log.error("Identity service unavailable: {}", ex.getMessage());
                    return reject(exchange, "Authentication service unavailable");
                });
    }

    private boolean isWhitelisted(String path) {
        return whitelist.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> reject(ServerWebExchange exchange, String reason) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json");
        byte[] body = ("{\"error\":\"" + reason + "\"}").getBytes();
        return response.writeWith(Mono.just(response.bufferFactory().wrap(body)));
    }

    @Override
    public int getOrder() {
        // Run after RequestIdFilter (-100) but before routing
        return -90;
    }
}

