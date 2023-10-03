package com.example.test.mapper;


import com.example.test.dto.BookingDto;
import com.example.test.dto.HotelDto;

import com.example.test.entity.Hotel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper
{

    HotelDto toDto(Hotel baseEntityPram);
    Hotel toEntity(HotelDto baseDtoPram);

    List<HotelDto> toDto(List<Hotel> baseEntityPram);

    List<Hotel> toEntity(List<HotelDto> baseDtoPram);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Hotel updateEntityFromDto (BookingDto hotelDto, @MappingTarget Hotel hotel);

}
