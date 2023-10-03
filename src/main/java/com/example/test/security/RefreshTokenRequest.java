package com.example.test.security;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenRequest {

    @NotBlank(message = "refresh token is mandatory")
    private String refreshToken;
}
