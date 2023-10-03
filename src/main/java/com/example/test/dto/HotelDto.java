package com.example.test.dto;

import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import lombok.Data;
import org.springframework.jdbc.object.GenericStoredProcedure;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class HotelDto
{

    private Integer id;
    private String HotelType;
    @NotBlank(message = "Full Name is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Size(max = 205, message = "Name's max length allowed is 205 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String HotelName;
    private String hotelRent;

}
