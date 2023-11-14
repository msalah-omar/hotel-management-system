package com.example.test.controller;

import com.example.test.dto.ChangePasswordDto;
import com.example.test.dto.UserDto;
import com.example.test.handler.UserHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserHandler userHandler;

    @GetMapping
    @Operation(summary = "Get All Users Paged")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(userHandler.getAll(page, size));
    }

    @PostMapping
    @Operation(summary = "add New User")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody UserDto usersDto) {
        return userHandler.save(usersDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get User By Id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return userHandler.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete User By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return userHandler.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update User")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)@PathVariable Integer id, @RequestBody UserDto usersDto) {
        return userHandler.update(id, usersDto);
    }
    @GetMapping("/search-user")
    public ResponseEntity<?> getSearchUser(@RequestParam(value = "username") String username) {
        return userHandler.getSearchUser(username);
    }

    @PostMapping("/change-password/{id}")
    @Operation(summary = " User")
    public ResponseEntity<?> changePassword( @RequestBody ChangePasswordDto changePasswordDto) {
        return userHandler.changePassword(changePasswordDto);
    }
}
