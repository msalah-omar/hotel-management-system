package com.example.test.handler;


import com.example.test.dto.BookingDto;
import com.example.test.dto.HotelDto;
import com.example.test.dto.commen.PaginatedResultDto;

import com.example.test.dto.Response;
import com.example.test.exception.ErrorCodes;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.exception.ResourceRelatedException;
import com.example.test.mapper.HotelMapper;
import com.example.test.mapper.PaginationMapper;
import com.example.test.service.HotelService;
import com.example.test.entity.Hotel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class HotelHandler
{
    private HotelService hotelService;
    private PaginationMapper paginationMapper;
    private HotelMapper mapper;

    public ResponseEntity<?> save(HotelDto dto)
    {
        Hotel hotel = mapper.toEntity(dto);
        hotelService.save(hotel);
        return ResponseEntity.ok(mapper.toDto(hotel));

    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        List<Hotel> hotels = hotelService.getAll(page, size);
        Page<Hotel> pages = new PageImpl<Hotel>(hotels, PageRequest.of(page, size), hotels.size());
        PaginatedResultDto<HotelDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(mapper.toDto(pages.getContent()));
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(pages));

        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getByName(String name)
    {
        Hotel hotels = hotelService .getByName(name);
        return ResponseEntity.ok(mapper.toDto(hotels));
    }

    public ResponseEntity<?> getById(Integer id)
    {
        Optional<Hotel> hotels = hotelService.getById(id);
        return ResponseEntity.ok(hotels);
    }

    public ResponseEntity<?> update(Integer id, BookingDto hotelDto)
    {

        Hotel hotel=hotelService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Hotel.class.getSimpleName(),id));
        mapper.updateEntityFromDto(hotelDto, hotel);
        hotelService.update(hotel);
        HotelDto dto = mapper.toDto(hotel);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        Hotel hotel = hotelService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Hotel.class.getSimpleName(), id));
        try
        {
            hotelService.delete(hotel);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(Hotel.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
