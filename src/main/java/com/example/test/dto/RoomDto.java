package com.example.test.dto;

import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoomDto
{
    private Integer id;
    private Integer version;
    private Integer price;

    @NotNull( message = "The Number is mandatory"  , groups = {InsertValidation.class, UpdateValidation.class})
    private Integer number;
    @NotBlank( message = "The Floor is mandatory"  , groups = {InsertValidation.class, UpdateValidation.class})
    private String floor;

    private Boolean complete;

    private HotelDto hotel;

}
