package com.example.test.dto;

import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.aspectj.apache.bcel.ExceptionConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;


@Data
public class BookingDto
{

    private Integer id;
    private String type;
    private String description;
    private LocalDate FromDate;
    private LocalDate today;
    private LocalDate ToDate;
    private HotelDto hotel;
//    private CustomerDto customer;
    private RoomDto room;
    private Boolean confirm;
    private UserDto user;

}
