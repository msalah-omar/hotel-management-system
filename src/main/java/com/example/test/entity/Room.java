package com.example.test.entity;

import com.example.test.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;

@Entity
@Data
@Table(name = "Room")

public class Room extends JPAEntity
{
    @Column(name = "NUMBER")
    private Integer number;

    @Column (name = "PRICE")
    private Integer price;

    @Column (name = "FLOOR")
    private String floor;

    @Column (name = "COMPLETE")
    private Boolean complete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;




}
