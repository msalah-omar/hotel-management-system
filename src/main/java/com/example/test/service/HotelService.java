package com.example.test.service;


import com.example.test.entity.Hotel;
import com.example.test.repository.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelService
{
    private HotelRepository hotelRepository;

    public List<Hotel> getAll(Integer page, Integer size)
    {
        Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(page, size));
        return hotelPage.getContent();
    }

    public Optional<Hotel> getById(Integer id)
    {
        return hotelRepository.findById(id);
    }

    public Hotel getByName(String name)
    {
        return hotelRepository.findByName(name);
    }

    public Hotel save(Hotel hotel)
    {
        return hotelRepository.save(hotel);
    }

    public void delete(Hotel hotel)
    {
        hotelRepository.delete(hotel);
    }


    public Hotel update(Hotel hotel)
    {
        hotelRepository.save(hotel);
        return hotel;
    }

    public void deleteById(Integer hotelId)
    {
        hotelRepository.deleteById(hotelId);
    }

}

