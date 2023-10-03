package com.example.test.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Response
{
    private String massage;

    private LocalDate endDate;

    private Long dayUntilEnd;


    public Response(String massage, LocalDate endDate, Long dayUntilEnd )
    {
        this.massage = massage;
        this.endDate = endDate;
        this.dayUntilEnd = dayUntilEnd;

    }

    public Response(String massage, LocalDate endDate)
    {
        this.massage = massage;
        this.endDate = endDate;
    }

    public Response(String massage)
    {
        this.massage = massage;
    }

    public Response(String massage,String  isAvailable)
    {
        this.massage = massage;
        this.massage = isAvailable;
    }

    public Response()
    {
    }
}
