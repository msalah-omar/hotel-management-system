package com.example.test.controller;

import com.example.test.dto.RoomTypeDto;
import com.example.test.dto.SafeDto;
import com.example.test.handler.RoomTypeHandler;
import com.example.test.handler.SafeHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Safe")
@RestController
@AllArgsConstructor
@RequestMapping("/safe")
public class SafeController
{
    private SafeHandler safeHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all safe")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return safeHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get safe by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return safeHandler.getById(id);
    }

    @GetMapping("/{hotelId}/current-safe-balance")
    public ResponseEntity<?> getShowSafeCurrentBalance(@PathVariable("hotelId") Integer hotelId)
    {
        return safeHandler.getShowSafeCurrentBalance(hotelId);
    }


    @PostMapping
    @Operation(summary = "Add", description = "this api for add new safe")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody SafeDto safeDto)
    {
        return safeHandler.save(safeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update safe")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @RequestBody SafeDto dto, @PathVariable Integer id)
    {
        return safeHandler.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete safe By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return safeHandler.delete(id);
    }
}
