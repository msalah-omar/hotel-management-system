package com.example.test.mapper;

import com.example.test.dto.BookingDto;
import com.example.test.dto.RoomDto;
import com.example.test.entity.Booking;
import com.example.test.entity.Room;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper
{
    RoomDto toDto(Room baseEntityPram);
    Room toEntity(RoomDto baseDtoPram);



    List<RoomDto> toDto(List<Room> baseEntityPram);
    List<Room> toEntity(List<RoomDto> baseDtoPram);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Room updateEntityFromDto (RoomDto roomDto, @MappingTarget Room room);
}
