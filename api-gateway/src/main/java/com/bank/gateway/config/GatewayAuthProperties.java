package com.bank.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "gateway.auth")
public class GatewayAuthProperties {

    private String identityServiceUrl;
    private List<String> whitelist = new ArrayList<>();

    public String getIdentityServiceUrl() { return identityServiceUrl; }
    public void setIdentityServiceUrl(String identityServiceUrl) { this.identityServiceUrl = identityServiceUrl; }

    public List<String> getWhitelist() { return whitelist; }
    public void setWhitelist(List<String> whitelist) { this.whitelist = whitelist; }
}