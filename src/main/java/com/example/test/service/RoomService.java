package com.example.test.service;

import com.example.test.entity.Room;
import com.example.test.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoomService
{
    private RoomRepository roomRepository;

    public Page<Room> getAll(Integer page, Integer size)
    {
        return (Page<Room>) roomRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Room> getById(Integer id)
    {
        return roomRepository.findById(id);
    }

    public Page<Room> getByRoomComplete(Boolean complete, Integer page, Integer size)
    {
        return roomRepository.findByRoomComplete(complete, PageRequest.of(page, size));
    }

    public Page<Room> getByRoomFull(Integer page, Integer size)
    {
        return roomRepository.findByRoomFull(PageRequest.of(page, size));
    }

    public Room save(Room room)
    {
        return roomRepository.save(room);
    }

    public Room update(Room room)
    {
        return roomRepository.save(room);
    }

    public Optional<Room> findRoomNumber(Integer number, Integer hotelId)
    {
        return Optional.ofNullable(roomRepository.findByNumber(number, hotelId));
    }


    public List<Room> delete(Room room)
    {
        roomRepository.delete(room);
        return null;
    }
}
