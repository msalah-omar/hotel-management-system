package com.example.test.service;

import com.example.test.entity.RoomType;
import com.example.test.entity.Safe;
import com.example.test.repository.RoomTypeRepository;
import com.example.test.repository.SafeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SafeService
{
    private SafeRepository safeRepository;

    public List<Safe> getAll(Integer page, Integer size)
    {
        Page<Safe> safePage = safeRepository.findAll(PageRequest.of(page, size));
        return safePage.getContent();
    }

    public Optional<Safe> getById(Integer id)
    {
        return safeRepository.findById(id);
    }

    public Safe save(Safe safe)
    {
        return safeRepository.save(safe);
    }


    public Safe update(Safe safe)
    {
        safeRepository.save(safe);
        return safe;
    }

    public List<Safe> delete(Safe safe)
    {
        safeRepository.delete(safe);
        return null;
    }

    public Safe getByHotelId(Integer hotelId) {
        return safeRepository.findByHotelId(hotelId);
    }

    public double getShowSafeCurrentBalance(Integer hotelId) {
        return safeRepository.getShowSafeCurrentBalance(hotelId);
    }
}
