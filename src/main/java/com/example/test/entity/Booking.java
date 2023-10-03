package com.example.test.entity;

import com.example.test.entity.commen.JPAEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
//import org.joda.time.Days;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Data
@Table(name = "booking")
public class Booking extends JPAEntity
{



    @Column (name = "TYPE")
    private String type;

    @Column (name = "from_date")
    private LocalDate fromDate;

    @Column (name = "today_date")
    private LocalDate todayDate;

    @Column (name = "to_date")
    private  LocalDate toDate;

    @Column (name = "confirm")
    private Boolean confirm;


    @Column (name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;


}
