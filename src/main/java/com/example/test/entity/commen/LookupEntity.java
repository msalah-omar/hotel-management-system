package com.example.test.entity.commen;

import com.example.test.entity.commen.JPAEntity;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class LookupEntity extends JPAEntity
{
    private String arabicName;
    private String englishName;
    private String code;
    private boolean enabled;


}
