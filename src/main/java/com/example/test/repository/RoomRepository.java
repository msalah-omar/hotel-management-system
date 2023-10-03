package com.example.test.repository;

import com.example.test.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room , Integer>
{
    @Query(" select r from Room r where r.number=:number and r.hotel.id = :hotelId")
    Room findByNumber (@Param("number") Integer number,
                       @Param("hotelId") Integer hotelId);

//    @Query(value = "select r from Room r WHERE r.number = :number ")
//    Room findByNumber(@Param("Number") Integer number);

    @Query(value = "select r from Room r where r.complete =:complete ")
    Page<Room> findByRoomComplete(@Param("complete") Boolean complete, Pageable pageable);

    @Query(value = "select r from Room r where r.complete =true")
    Page<Room> findByRoomFull(Pageable pageable);

    @Query("SELECT r FROM Room r JOIN Booking b ON r.id = b.room.id")
    Room findAllReservedRoom();

}
