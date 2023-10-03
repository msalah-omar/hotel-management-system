package com.example.test.security;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {

//    @NotBlank(message = "username is mandatory")
    private String username;

//    @NotBlank(message = "password is mandatory")
    private String password;

}
