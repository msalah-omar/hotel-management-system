package com.example.test.mapper;

import com.example.test.dto.BookingDto;
import com.example.test.dto.HotelDto;
import com.example.test.dto.SafeDto;
import com.example.test.entity.Hotel;
import com.example.test.entity.Safe;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")

public interface SafeMapper
{
    SafeDto toDto(Safe baseEntityPram);
    Safe toEntity(SafeDto baseDtoPram);

    List<SafeDto> toDto(List<Safe> baseEntityPram);

    List<Safe> toEntity(List<SafeDto> baseDtoPram);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Safe updateEntityFromDto (SafeDto safeDto, @MappingTarget Safe safe);
}
