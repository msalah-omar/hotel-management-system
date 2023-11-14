package com.example.test.mapper.commen;


import com.example.test.dto.commen.RestDto;
import com.example.test.entity.User;
import com.example.test.entity.commen.JPAEntity;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Optional;

public interface JPAEntityMapper<T extends JPAEntity, S extends RestDto> {

    T toEntity(S s);

    S toDto(T t);

    List<T> toEntity(List<S> dtoList);

    List<S> toDto(List<T> dtoList);

    T updateEntityFromDto(S s, @MappingTarget T t);
}