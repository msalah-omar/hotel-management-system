package com.example.test.mapper;

import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.mapper.commen.JPAEntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends JPAEntityMapper<User ,UserDto>
{
}
