package com.example.test.security;

import com.example.test.security.AuthRequest;
import com.example.test.security.TokenProvider;
import com.example.test.security.TokenType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@Log4j2
public class AuthHandler {


    private  AuthenticationManager authenticationManager;
    private  TokenProvider tokenProvider;

    public ResponseEntity<?> login(AuthRequest  authRequest)
    {
        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.generateToken(authRequest.getUsername(), TokenType.ACCESS_TOKEN);
        String refreshToken = tokenProvider.generateToken(authRequest.getUsername(),TokenType.REFRESH_TOKEN);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access token", accessToken);
        tokens.put("refresh token", refreshToken);

        return ResponseEntity.ok(tokens);
    }

    public ResponseEntity<?> refresh(String refreshToken) {
        boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);
        if (!refreshTokenValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUsername = tokenProvider.getUsernameFromToken(refreshToken);

        String newAccessToken = tokenProvider.generateToken(currentUsername,TokenType.ACCESS_TOKEN);
        String newRefreshToken = tokenProvider.generateToken(currentUsername,TokenType.REFRESH_TOKEN);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access token", newAccessToken);
        tokens.put("refresh token", newRefreshToken);

        return ResponseEntity.ok(tokens);
    }
}


