package com.example.test.controller;

import com.example.test.dto.BookingDto;
import com.example.test.dto.RoomDto;
import com.example.test.entity.Booking;
import com.example.test.entity.Room;
import com.example.test.handler.BookingHandler;
import com.example.test.handler.RoomHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Room")
@AllArgsConstructor
@RestController
@RequestMapping("/room")

public class RoomController
{
    private RoomHandler roomHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all customers")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page" , defaultValue = "0") Integer page ,
            @RequestParam (value = "size" , defaultValue = "10") Integer size)
    {
        return roomHandler.getAll(page, size);
    }

    @GetMapping("/not-complete")
    @Operation(summary = "Get All", description = "this api for get all not complete rooms")
    public ResponseEntity<?> getByRoomComplete(
            @RequestParam(value = "complete") Boolean complete,
            @RequestParam(value = "page" , defaultValue = "0") Integer page ,
            @RequestParam (value = "size" , defaultValue = "10") Integer size)
    {
        return roomHandler.getByRoomComplete(complete,page, size);
    }

//    @GetMapping("/complete")
//    @Operation(summary = "Get All", description = "this api for get all not complete rooms")
//    public ResponseEntity<?> getByRoomFull(
//            @RequestParam(value = "page" , defaultValue = "0") Integer page ,
//            @RequestParam (value = "size" , defaultValue = "10") Integer size)
//    {
//        return roomHandler.getByRoomFull(page, size);
//    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get room by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return roomHandler.getById(id);
    }

    @GetMapping("/{id}/installments")
    @Operation(summary = "Get All Installments", description = "this api for get all installments of room")
    public ResponseEntity<?> getPaymentInstallments(@PathVariable("id") Integer id)
    {
        return roomHandler.getById(id);
    }


    @PostMapping
    @Operation(summary = "Add", description = "this api for add new room")
    public ResponseEntity<?> save(@RequestBody RoomDto roomDto) {
        return roomHandler.save(roomDto);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update room")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @RequestBody RoomDto dto ,@PathVariable Integer id) {
        return roomHandler.update(dto, id);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "delete room By Id")
    public ResponseEntity<?> delete(Integer id) {
        return roomHandler.delete(id);
    }

}
