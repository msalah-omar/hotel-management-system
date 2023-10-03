package com.example.test.repository;

import com.example.test.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository <Hotel, Integer>
{
    @Query(" select H from Hotel H where name=:name")
    Hotel findByName (@Param("name") String name);


}
