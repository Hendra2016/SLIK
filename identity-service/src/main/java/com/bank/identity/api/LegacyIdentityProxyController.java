package com.bank.identity.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    public LegacyIdentityProxyController(RestTemplate legacyRestTemplate) {
        this.restTemplate = legacyRestTemplate;
    }

    @RequestMapping(
            value = {"/{resource}", "/{resource}/{*remaining}"},
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> proxyIdentity(
            @PathVariable("resource") String resource,
            @PathVariable(value = "remaining", required = false) String remaining,
            @RequestBody(required = false) String body,
            HttpServletRequest request) {

        String normalizedResource = resource == null ? "" : resource.toLowerCase(Locale.ROOT);
        if (!normalizedResource.matches(allowedResourcePattern)) {
            return ResponseEntity.badRequest().body(new LegacyRestResponse(0, "identity resource not allowed", null, 0));
        }

        String targetUrl = buildTargetUrl(normalizedResource, remaining, request.getQueryString());
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        HttpHeaders headers = new HttpHeaders();
        copyHeader(request, headers, "Content-Type");
        copyHeader(request, headers, "Authorization");
        copyHeader(request, headers, "web-creden");
        copyHeader(request, headers, "X-Request-Id");

        HttpEntity<String> entity = body == null ? new HttpEntity<>(headers) : new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(targetUrl, method, entity, String.class);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            copyHeader(request, responseHeaders, "X-Request-Id");
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

