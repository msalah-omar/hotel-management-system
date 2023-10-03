package com.example.test.security;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "REST API for Authentication")
@Log4j2
public class AuthController {


    private  AuthHandler authHandler;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest)
    {
        return authHandler.login(authRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> login(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest)
    {
        return authHandler.refresh(refreshTokenRequest.getRefreshToken());
    }

}
