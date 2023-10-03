package com.example.test.controller;


import com.example.test.dto.HotelDto;
import com.example.test.dto.RoomTypeDto;
import com.example.test.handler.HotelHandler;
import com.example.test.handler.RoomTypeHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "RoomType")
@RestController
@AllArgsConstructor
@RequestMapping("/roomType")
public class RoomTypeController
{
    private RoomTypeHandler roomTypeHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all roomType")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return roomTypeHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get roomType by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return roomTypeHandler.getById(id);
    }


    @PostMapping
    @Operation(summary = "Add", description = "this api for add new roomType")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody RoomTypeDto roomTypeDto)
    {
        return roomTypeHandler.save(roomTypeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update roomType")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @RequestBody RoomTypeDto dto ,@PathVariable Integer id)
    {
        return roomTypeHandler.update(id , dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete roomType By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return roomTypeHandler.delete(id);
    }
}
