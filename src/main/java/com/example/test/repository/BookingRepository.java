package com.example.test.repository;


import com.example.test.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>
{

    @Query(value = "select b from Booking b where b.room.complete = :complete")
    Optional<Booking> findByRoomId(@Param("complete") Boolean complete);

    @Query(value = "select b from Booking b ")
    Page<Booking> findAllBooking(Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.hotel.id = :hotelId AND b.room.id = :roomId")
    Booking findByRoomIdAndHotelId(@Param("hotelId") Integer hotelId, Integer roomId);

    @Query(value = "select b from Booking b JOIN FETCH b.hotel JOIN FETCH b.room JOIN FETCH b.user where b.id = :id")
    Booking getById(@Param("id") Integer id);

    @Query(value = "select b from Booking b JOIN  FETCH b.user where b.id = :id" )
    List<Booking> getSearchCustomerReservationNumberDate(@Param("id") Integer id);

    @Query(value = "select b from Booking b where b.user.id = :id ")
    List<Booking> userViewsBooking(@Param("id") Integer id);


    @Query(value = "SELECT b FROM Booking b WHERE (:fromDate IS NULL OR b.fromDate >= :fromDate) AND (:toDate IS NULL OR b.toDate <= :toDate)")
    List<Booking> getReviewRoomsBasedTimeAvailability(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);


//    @Query("SELECT DATEDIFF(DATE(b.toDate)), (DATE(b.fromDate)) FROM Booking b WHERE b.room.id = :roomId AND b.hotel.id = :hotelId")
//    Integer findBookingRoomPeriod(@Param("roomId") Integer roomId, @Param("hotelId") Integer hotelId);
}
