package com.example.test.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

@Data
public class RoomTypeDto
{

    private String nameArabic;
    private String nameEnglish;

}
