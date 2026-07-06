package com.aurora.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WellKnownController {

    @GetMapping("/.well-known/appspecific/com.chrome.devtools.json")
    @ResponseBody
    public ResponseEntity<Void> chromeDevTools() {
        return ResponseEntity.noContent().build();
    }
}