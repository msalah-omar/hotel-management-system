package com.example.test.repository;

import com.example.test.entity.Safe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SafeRepository extends JpaRepository <Safe , Integer>
{

    @Query("SELECT s FROM Safe s WHERE s.hotel.id = :hotelId")
    Safe findByHotelId(@Param("hotelId") Integer hotelId);

    @Query ("SELECT s.cacheIn FROM Safe s WHERE s.hotel.id = :hotelId")
    Double getShowSafeCurrentBalance(@Param("hotelId") Integer hotelId);

}
