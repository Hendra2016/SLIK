package com.bank.identity.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LegacyIdentityProxyController.class)
class LegacyIdentityProxyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate legacyRestTemplate;

    @Test
    void forwardsAllowedIdentityPath() throws Exception {
        given(legacyRestTemplate.exchange(
                eq("http://localhost:8082/bank-core/login/u/p"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)))
                .willReturn(new ResponseEntity<>("{\"status\":1}", HttpStatus.OK));

        mockMvc.perform(get("/bank-core/login/u/p"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":1}"));
    }

    @Test
    void rejectsNonIdentityPath() throws Exception {
        mockMvc.perform(get("/bank-core/a01/all"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(0));
    }
}

