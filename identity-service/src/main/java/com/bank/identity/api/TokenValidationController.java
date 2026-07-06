package com.bank.identity.api;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Token validation endpoint called by api-gateway before routing requests.
 * Validates the web-creden token by calling bank-core's menu endpoint.
 * If bank-core accepts the token → 200 OK (valid).
 * If bank-core rejects it     → 401 Unauthorized (invalid/expired).
 *
 * bank-core stays completely clean — no auth code needed there.
 */
@RestController
@RequestMapping("/auth")
public class TokenValidationController {

    private static final Logger log = LoggerFactory.getLogger(TokenValidationController.class);
    private static final String WEB_CREDEN_HEADER = "web-creden";

    private final RestTemplate restTemplate;

    @Value("${legacy.bank-core.base-url:http://localhost:8082/bank-core/}")
    private String legacyBaseUrl;

    @Value("${auth.validate.probe-path:auth/validate}")
    private String probePath;

    public TokenValidationController(RestTemplate legacyRestTemplate) {
        this.restTemplate = legacyRestTemplate;
    }

    /**
     * Called by gateway TokenValidationFilter.
     * Returns 200 if token is valid, 401 if not.
     */
    @GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validate(HttpServletRequest request) {
        String token = request.getHeader(WEB_CREDEN_HEADER);

        if (token == null || token.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("valid", "false", "reason", "missing token"));
        }

        // Probe bank-core with the token — if accepted, token is valid
        String probeUrl = normalizeBaseUrl() + probePath;
        HttpHeaders headers = new HttpHeaders();
        headers.set(WEB_CREDEN_HEADER, token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    probeUrl, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.debug("Token validated successfully via probe: {}", probeUrl);
                return ResponseEntity.ok(Map.of("valid", "true"));
            } else {
                log.warn("Token probe returned non-2xx: {}", response.getStatusCode());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("valid", "false", "reason", "token rejected"));
            }
        } catch (HttpClientErrorException e) {
            log.warn("Token validation rejected by bank-core: {}", e.getStatusCode());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("valid", "false", "reason", "token invalid or expired"));
        } catch (RestClientException e) {
            log.error("Bank-core unreachable during token validation: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("valid", "false", "reason", "auth backend unavailable"));
        }
    }

    private String normalizeBaseUrl() {
        if (legacyBaseUrl == null || legacyBaseUrl.isBlank()) {
            return "http://localhost:8082/bank-core/";
        }
        return legacyBaseUrl.endsWith("/") ? legacyBaseUrl : legacyBaseUrl + "/";
    }
}

