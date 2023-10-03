package com.example.test.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDto
{
    private Integer id;
    private String name;
    private LocalDate date;
    private String mobile;
    private Integer value;
    private BookingDto booking;

}
