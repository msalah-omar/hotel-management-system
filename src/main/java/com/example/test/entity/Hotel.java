package com.example.test.entity;


import com.example.test.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "hotel")

public class Hotel extends JPAEntity

{
    @Column(name = "HOTEL_TYPE" )
    private String hotelType;

    @Column (name = "HOTEL_NAME")
    private String hotelName;

    @Column (name = "HOTEL_RENT")
    private String hotelRent;

    @Column (name = "ROOM_COUNT")
    private String roomCount;



}
