package com.example.test.controller;

import com.example.test.dto.BookingDto;
import com.example.test.dto.HotelDto;
import com.example.test.entity.Hotel;
import com.example.test.handler.HotelHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Hotel")
@RequestMapping("/hotel")
@RestController
@AllArgsConstructor
public class HotelController
{
    private HotelHandler hotelHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all customers")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return hotelHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get hotel by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return hotelHandler.getById(id);
    }

    @GetMapping("/{id}/installments")
    @Operation(summary = "Get All Installments", description = "this api for get all installments of hotel")
    public ResponseEntity<?> getCustomerInstallments(@PathVariable("id") Integer id)
    {
        return hotelHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new hotel")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody HotelDto hotelDto)
    {
        return hotelHandler.save(hotelDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update hotel")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)  @RequestBody BookingDto dto , @PathVariable Integer id)
    {
        return hotelHandler.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Hotel By Id")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id)
    {
        return hotelHandler.delete(id);
    }

}
