package com.example.test.mapper;


import com.example.test.dto.RoleDto;
import com.example.test.entity.Role;
import com.example.test.mapper.commen.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends JPAEntityMapper<Role, RoleDto> {
}
