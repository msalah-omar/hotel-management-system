package com.example.test.entity;

import com.example.test.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "safe")

public class Safe extends JPAEntity
{
    @Column(name = "CACHE_iN")
    private double cacheIn;

    @Column (name = "CACHE_OUT")
    private double cacheOut;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;
}
