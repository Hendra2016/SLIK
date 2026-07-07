package com.bank.identity.api;

import com.bank.dto.*;
import com.bank.identity.security.JwtTokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@RestController
@RequestMapping("/bank-core")
public class LegacyIdentityProxyController {

    private final RestTemplate restTemplate;

    @Value("${legacy.bank-core.base-url:http://localhost:8082/bank-core/}")
    private String legacyBaseUrl;

    @Value("${legacy.identity.allowed-pattern:^(login|menu|bankuser)$}")
    private String allowedResourcePattern;

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    public LegacyIdentityProxyController(RestTemplate legacyRestTemplate,JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper) {
        this.restTemplate = legacyRestTemplate;
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = {"/{resource}", "/{resource}/{*remaining}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> proxyIdentity(@PathVariable("resource") String resource, @PathVariable(value = "remaining", required = false) String remaining, @RequestBody(required = false) String body, HttpServletRequest request) {
        String normalizedResource = normalizeResource(resource);
        if (!isAllowedResource(normalizedResource)) {
            return ResponseEntity.badRequest().body(new LegacyRestResponse(0, "identity resource not allowed", null, 0));
        }
        String targetUrl = buildTargetUrl(normalizedResource, remaining, request.getQueryString());
        HttpHeaders headers = buildHeaders(request);
        return forwardRequest(targetUrl, headers, body, request.getMethod());
    }

    private String normalizeResource(String resource) {
        return resource == null ? "" : resource.toLowerCase(Locale.ROOT);
    }

    private boolean isAllowedResource(String resource) {
        return resource.matches(allowedResourcePattern);
    }

    private HttpHeaders buildHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        copyHeader(request, headers, "Content-Type");
        copyHeader(request, headers, "Authorization");
        copyHeader(request, headers, "web-creden");
        copyHeader(request, headers, "X-Request-Id");
        return headers;
    }

    private ResponseEntity<?> processLoginResponse(ResponseEntity<String> response)
            throws JsonProcessingException {
        LegacyRestResponse rest = objectMapper.readValue(response.getBody(), LegacyRestResponse.class);
        UserDto user = objectMapper.convertValue(rest.getContents(), UserDto.class);
        String token = jwtTokenProvider.generateToken(user);
        user.setToken(token);
        rest.setContents(user);
        return ResponseEntity.status(response.getStatusCode()).
                contentType(MediaType.APPLICATION_JSON).body(rest);
    }

    private ResponseEntity<?> forwardRequest(String targetUrl, HttpHeaders headers, String body, String method) {
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        HttpEntity<String> entity = body == null ? new HttpEntity<>(headers) : new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(targetUrl, httpMethod, entity, String.class);
            if (targetUrl.endsWith("/login")) {
                try {
                    return processLoginResponse(response);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(response.getBody(), responseHeaders, response.getStatusCode());
        } catch (RestClientException ex) {
            return ResponseEntity.status(503).body(new LegacyRestResponse(0, "legacy identity unavailable", null, 0));
        }
    }

    private String buildTargetUrl(String resource, String remaining, String queryString) {
        StringBuilder url = new StringBuilder(normalizeBaseUrl()).append(resource);
        if (remaining != null && !remaining.isBlank()) {
            url.append('/').append(remaining);
        }
        if (queryString != null && !queryString.isBlank()) {
            url.append('?').append(queryString);
        }

        return url.toString();
    }

    private String normalizeBaseUrl() {
        if (legacyBaseUrl == null || legacyBaseUrl.isBlank()) {
            return "http://localhost:8082/bank-core/";
        }
        return legacyBaseUrl.endsWith("/") ? legacyBaseUrl : legacyBaseUrl + "/";
    }

    private void copyHeader(HttpServletRequest request, HttpHeaders headers, String headerName) {
        String value = request.getHeader(headerName);
        if (value != null && !value.isBlank()) {
            headers.add(headerName, value);
        }
    }
}