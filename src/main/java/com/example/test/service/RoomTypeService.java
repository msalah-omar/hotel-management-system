package com.example.test.service;


import com.example.test.entity.Hotel;
import com.example.test.entity.Room;
import com.example.test.entity.RoomType;
import com.example.test.repository.HotelRepository;
import com.example.test.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomTypeService
{

    private RoomTypeRepository roomTypeRepository;

    public List<RoomType> getAll(Integer page, Integer size)
    {
        Page<RoomType> roomTypePage = roomTypeRepository.findAll(PageRequest.of(page, size));
        return roomTypePage.getContent();
    }

    public Optional<RoomType> getById(Integer id)
    {
        return roomTypeRepository.findById(id);
    }

    public RoomType save(RoomType roomType)
    {
        return roomTypeRepository.save(roomType);
    }


    public RoomType update(RoomType roomType)
    {
        roomTypeRepository.save(roomType);
        return roomType;
    }

    public List<RoomType> delete(RoomType roomType)
    {
         roomTypeRepository.delete(roomType);
        return null;
    }
}
