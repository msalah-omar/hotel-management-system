package com.example.test.service;


import com.example.test.dto.BookingDto;
import com.example.test.entity.Booking;
import com.example.test.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService
{
    private BookingRepository bookingRepository;

    public Page<Booking> getAll(Integer page, Integer size)
    {
        return bookingRepository.findAllBooking(PageRequest.of(page, size));
    }

    public Booking getById(Integer id)
    {
        return bookingRepository.getById(id);
    }

    public Booking getByRoomIdAndHotelId(Integer hotelId, Integer roomId)
    {
        return bookingRepository.findByRoomIdAndHotelId(hotelId, roomId);
    }

    public List<Booking> userViewsBooking(Integer id)
    {
        return bookingRepository.userViewsBooking(id);
    }

    public List<Booking> getReviewRoomsBasedTimeAvailability(LocalDate fromDate, LocalDate toDate)
    {
        return bookingRepository.getReviewRoomsBasedTimeAvailability(fromDate, toDate);
    }

    
    public Optional<Booking> getByRoomId(Boolean complete)
    {
        return bookingRepository.findByRoomId(complete);
    }

    public Booking save(Booking booking)
    {
        return bookingRepository.save(booking);
    }

    public Booking update(Booking booking)
    {
        return bookingRepository.save(booking);
    }


    public List<Booking> delete(Booking booking)
    {
        bookingRepository.delete(booking);
        return null;
    }


    public Optional<Booking> findById(Integer id)
    {
        return bookingRepository.findById(id);
    }
}
