package com.example.test.repository;

import com.example.test.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository <RoomType ,Integer>
{
}
