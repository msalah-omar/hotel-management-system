package com.example.test.dto;


import com.example.test.dto.commen.RestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto extends RestDto
{

    private String username;
    private String password;


    @JsonIgnore
    public String getPassword()
    {
        return password;
    }
}
