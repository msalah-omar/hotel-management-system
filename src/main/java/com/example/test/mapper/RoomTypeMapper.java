package com.example.test.mapper;

import com.example.test.dto.BookingDto;
import com.example.test.dto.RoomTypeDto;
import com.example.test.entity.Booking;
import com.example.test.entity.RoomType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper
{

    RoomTypeDto toDto(RoomType baseEntityPram);
    RoomType toEntity(RoomTypeDto baseDtoPram);

    List<RoomTypeDto> toDto(List<RoomType> baseEntityPram);
    List<RoomType> toEntity(List<RoomTypeDto> baseDtoPram);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract RoomType updateEntityFromDto (RoomTypeDto roomTypeDto, @MappingTarget RoomType roomType);
}
