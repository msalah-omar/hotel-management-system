package com.example.test.handler;

import com.example.test.dto.BookingDto;
import com.example.test.dto.commen.PaginatedResultDto;
import com.example.test.entity.Booking;
import com.example.test.entity.Hotel;
import com.example.test.entity.Response;
import com.example.test.entity.User;
import com.example.test.exception.ErrorCodes;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.exception.ResourceRelatedException;
import com.example.test.mapper.BookingMapper;
import com.example.test.mapper.PaginationMapper;
import com.example.test.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookingHandler
{
    private HotelService hotelService;
    private UserService userService;
    private BookingService bookingService;
    private RoomService roomService;
    private PaginationMapper paginationMapper;
    private BookingMapper bookingMapper;



    public ResponseEntity<?> confirmBooking(Integer id)
    {
        Booking booking1 = bookingService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));

        Booking booking = bookingService.findById(id).get();
        booking.setConfirm(true);
        bookingService.save(booking);
        return ResponseEntity.ok("CONFIRMED");
    }

    public ResponseEntity<?> checkoutBooking(Integer id)

    {
        Booking booking1 = bookingService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(Booking.class.getSimpleName(), id));
        Booking booking = bookingService.findById(id).get();
        booking.setToDate(LocalDate.now());
        bookingService.save(booking);
        return ResponseEntity.ok("CHECKOUT");
    }

    public ResponseEntity<?> userViewsBooking(Integer id)
    {
        Booking booking1 = bookingService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(Booking.class.getSimpleName(), id));

        List<Booking> booking = bookingService.userViewsBooking(id);
        List<BookingDto> dtos = bookingMapper.toDto(booking);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getReviewRoomsBasedTimeAvailability(LocalDate fromDate, LocalDate toDate)
    {
        List<Booking> bookings = bookingService.getReviewRoomsBasedTimeAvailability(fromDate, toDate);
        List<BookingDto> dtos = bookingMapper.toDto(bookings);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getRemainDays(Integer hotelId, Integer roomId)
    {

        Booking booking = bookingService.getByRoomIdAndHotelId(hotelId, roomId);

        long differenceDate = 0;
        if (LocalDate.now().isBefore(booking.getFromDate()))
        {
            differenceDate = booking.getFromDate().until(booking.getToDate(), ChronoUnit.DAYS);
            return ResponseEntity.ok(new Response("room booking will start at: " + booking.getFromDate(), booking.getToDate(), differenceDate));
        } else if (LocalDate.now().equals(booking.getFromDate()) || LocalDate.now().isAfter(booking.getFromDate()))
        {
            if (LocalDate.now().equals(booking.getToDate()) || LocalDate.now().isAfter(booking.getToDate()))
                differenceDate = LocalDate.now().until(booking.getToDate(), ChronoUnit.DAYS);
            return ResponseEntity.ok(new Response("room booking end at " + booking.getToDate(), null, differenceDate));
        }
        return ResponseEntity.ok(new Response("the room will be completed for: " + differenceDate, null, null));
    }


    public ResponseEntity<?> save(BookingDto dto, Integer page, Integer size)
    {
        long differenceDate = dto.getFromDate().until(dto.getToDate(), ChronoUnit.DAYS);
        Booking booking = bookingMapper.toEntity(dto);
        booking.setConfirm(false);
        booking.setUser(userService.getById(dto.getUser().getId()).get());
        booking.setRoom(roomService.getById(dto.getRoom().getId()).get());
        booking.setHotel(hotelService.getById(dto.getHotel().getId()).get());

        Booking byRoomIdAndHotelId = bookingService.getByRoomIdAndHotelId(dto.getHotel().getId(), dto.getRoom().getId());
        if (byRoomIdAndHotelId != null)
        {
            if ((dto.getFromDate().isBefore(byRoomIdAndHotelId.getFromDate()) && dto.getToDate().isBefore(byRoomIdAndHotelId.getFromDate())) || (dto.getFromDate().isAfter(byRoomIdAndHotelId.getToDate()) && dto.getToDate().isAfter(byRoomIdAndHotelId.getToDate())))
            {
                bookingService.save(booking);
                return ResponseEntity.ok(new Response("** room is complete from :" + dto.getFromDate(), dto.getToDate(), differenceDate));
            }
            return ResponseEntity.ok(new Response("can not booking from :" + dto.getFromDate(), dto.getToDate(), null));
        }

        bookingService.save(booking);
        return ResponseEntity.ok(new Response("room is complete from :" + dto.getFromDate(), dto.getToDate(), differenceDate));
    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        Page<Booking> bookings = bookingService.getAll(page, size);
        List<BookingDto> dtos = bookingMapper.toDto(bookings.getContent());
        PaginatedResultDto<BookingDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(bookings));
        return ResponseEntity.ok(paginatedResultDto);
    }


    public ResponseEntity<?> getById(Integer id)
    {
        Optional<Booking> booking = bookingService.findById(id);
        return ResponseEntity.ok(booking);
    }

    public ResponseEntity<?> update(Integer id, BookingDto bookingDto)
    {
        Booking booking = bookingService.findById(id).orElseThrow(() -> new ResourceNotFoundException(Booking.class.getSimpleName(), id));
        bookingMapper.updateEntityFromDto(bookingDto, booking);
        bookingService.update(booking);
        BookingDto dto = bookingMapper.toDto(booking);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        Booking booking = bookingService.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
        try
        {
            if (booking.getConfirm())
            {
                return ResponseEntity.ok("can not deleted");
            }
            bookingService.delete(booking);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(User.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
