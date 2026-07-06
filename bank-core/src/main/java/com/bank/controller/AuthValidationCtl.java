package com.bank.controller;

import com.bank.dao.BankLoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Token validation endpoint called by identity-service.
 * Checks web-creden token against BankLogin table in database.
 * This is the ONLY place that knows if a token is valid or not.
 *
 * Called by: identity-service /auth/validate → this endpoint
 */
@RestController
@RequestMapping("/auth")
public class AuthValidationCtl {

    @Autowired
    private BankLoginDao bankLoginDao;

    /**
     * Validates web-creden token against BankLogin table.
     * Returns 200 if valid, 401 if not found/invalid.
     */
    @GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validate(
            @RequestHeader(value = "web-creden", required = false) String token) {

        if (token == null || token.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("valid", "false", "reason", "missing token"));
        }

        try {
            int loginId = bankLoginDao.validateToken(token);
            if (loginId > 0) {
                return ResponseEntity.ok(Map.of("valid", "true"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("valid", "false", "reason", "token not found"));
            }
        } catch (Exception e) {
            // validateToken throws or returns 0 if not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("valid", "false", "reason", "token invalid or expired"));
        }
    }
}

