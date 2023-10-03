package com.example.test.mapper;

import com.example.test.dto.BookingDto;

import com.example.test.dto.GenericDto;
import com.example.test.entity.Booking;

import com.example.test.entity.commen.JPAEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper
{

    BookingDto toDto(Booking baseEntityPram);
    Booking toEntity(BookingDto baseDtoPram);

    List<BookingDto> toDto(List<Booking> baseEntityPram);
    List<Booking> toEntity(List<BookingDto> baseDtoPram);



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Booking updateEntityFromDto (BookingDto bookingDto, @MappingTarget Booking booking);

}
