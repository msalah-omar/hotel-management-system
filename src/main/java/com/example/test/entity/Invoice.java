package com.example.test.entity;

import com.example.test.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Invoice")
@Data
public class Invoice extends JPAEntity
{

    @Column (name = "DATE")
    private LocalDate date;

    @Column (name = "MOBILE")
    private String mobile;

    @Column (name = "value")
    private Integer value;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;


}
