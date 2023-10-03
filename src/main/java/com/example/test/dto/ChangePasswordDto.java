package com.example.test.dto;


import lombok.Data;

@Data
public class ChangePasswordDto
{
//    private String password;
    private String oldPassword;
    private String newPassword;
    private String reNewPassword;
}
