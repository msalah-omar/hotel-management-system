package com.example.test.entity;

import com.example.test.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "RoomType")
public class RoomType extends JPAEntity
{
    @Column(name = "NAME_ARABIC")
    private String nameArabic;

    @Column(name = "NAME_ENGLISH")
    private String nameEnglish;


}
