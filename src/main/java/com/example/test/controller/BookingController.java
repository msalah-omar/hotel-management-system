package com.example.test.controller;

import com.example.test.dto.BookingDto;
import com.example.test.handler.BookingHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Tag(name = "Booking")
@AllArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController
{

    private BookingHandler bookingHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all bookings")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        return bookingHandler.getAll(page, size);
    }

    @GetMapping("/get-remain-days")
    public ResponseEntity<?> getRemainDays(@RequestParam(value = "hotelId") Integer hotelId,
                                           @RequestParam(value = "roomId") Integer roomId ) {
        return bookingHandler.getRemainDays(hotelId, roomId );
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get booking by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return bookingHandler.getById(id);
    }


    @PostMapping
    @Operation(summary = "Add", description = "this api for add new booking")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody BookingDto dto ,Integer page,Integer size)
    {
        return bookingHandler.save(dto,page,size);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update booking")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @RequestBody BookingDto dto , @PathVariable Integer id )
    {
        return bookingHandler.update(id,dto);
    }

    @GetMapping("/checkout-booking/{id}")
    public ResponseEntity<?> checkoutBooking(@PathVariable(value = "id") Integer id) {
        return bookingHandler.checkoutBooking(id);
    }
    @GetMapping("/confirm-booking/{id}")
    public ResponseEntity<?> confirmBooking(@PathVariable(value = "id") Integer id) {
        return bookingHandler.confirmBooking(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getReviewRoomsBasedTimeAvailability(@RequestParam(value = "fromDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate fromDate,
                                                                 @RequestParam(value = "toDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate toDate) {
        return bookingHandler.getReviewRoomsBasedTimeAvailability(fromDate,toDate);
    }

    @GetMapping("/views-booking")
    public ResponseEntity<?> userViewsBooking(@RequestParam(value = "id") Integer id) {
        return bookingHandler.userViewsBooking(id);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "delete payment By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        return bookingHandler.delete(id);
    }


}
