package com.example.test.handler;

import com.example.test.dto.ChangePasswordDto;
import com.example.test.dto.UserDto;
import com.example.test.dto.commen.PaginatedResultDto;
import com.example.test.entity.Response;
import com.example.test.entity.User;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.mapper.PaginationMapper;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserHandler {

    private final UserService userService;
    private final UserMapper userMapper;
    private PaginationMapper paginationMapper;
//    private ChangePasswordDto changePasswordDto;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<User> usersPage = userService.getAll(page, size);
        List<UserDto> usersDtoList = userMapper.toDto(usersPage.getContent());
        PaginatedResultDto<UserDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(usersDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(usersPage));
        return ResponseEntity.ok(paginatedResult);
    }


    public ResponseEntity<?> save(UserDto dto) {

        Optional<User> existedUsername= userService.getByUserName(dto.getUsername());
        if(existedUsername.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user=userMapper.toEntity(dto);
        userService.save(user);
        UserDto usersDto=userMapper.toDto(user);
        return ResponseEntity.created(URI.create("/user/" + usersDto.getId())).body(usersDto);
    }

    public ResponseEntity<?> getById(Integer id) {
        User user = userService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    public ResponseEntity<?> delete(Integer id) {
        User user = userService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
        userService.delete(user);
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<?> update(Integer userId, UserDto usersDto) {
        User user = userService.getById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), userId));
        Optional<User> existedUser=userService.getByUserName(usersDto.getUsername());
        if(existedUser.isPresent() && !existedUser.get().getId().equals(userId)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User entity = userMapper.updateEntityFromDto(usersDto, user);
        userService.update(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> changePassword(ChangePasswordDto changePasswordDto)
    {
        // TODO: get logged in user
        User user =userService.getByUserName(changePasswordDto.getOldPassword()).get(); // username
        if (!userService.oldPasswordIsValid(user, changePasswordDto.getOldPassword()))
        {
            return  ResponseEntity.ok(new Response("Incorrect old password"));
        }
        userService.changePassword(user, changePasswordDto.getNewPassword());
        return ResponseEntity.ok(new Response("Password Change Successfully"));
    }





}
