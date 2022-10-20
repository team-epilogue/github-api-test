package com.example.gitapitest.api.controller;

import com.example.gitapitest.api.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String code) {
        String accessToken = oAuthService.getAccessToken(code);
        return ResponseEntity.ok(accessToken);
    }

    @GetMapping("/user")
    public ResponseEntity<String> userName(@RequestParam String token) {
        String name = oAuthService.getUserName(token);
        return ResponseEntity.ok(name);
    }

}
