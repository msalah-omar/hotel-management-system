package com.example.test.handler;


import com.example.test.dto.BookingDto;
import com.example.test.dto.HotelDto;
import com.example.test.dto.RoomTypeDto;
import com.example.test.dto.commen.PaginatedResultDto;
import com.example.test.entity.Booking;
import com.example.test.entity.Hotel;
import com.example.test.entity.Response;
import com.example.test.entity.RoomType;
import com.example.test.exception.ErrorCodes;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.exception.ResourceRelatedException;
import com.example.test.mapper.HotelMapper;
import com.example.test.mapper.PaginationMapper;
import com.example.test.mapper.RoomTypeMapper;
import com.example.test.service.HotelService;
import com.example.test.service.RoomTypeService;
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
public class RoomTypeHandler
{

    private RoomTypeService roomTypeService;
    private PaginationMapper paginationMapper;
    private RoomTypeMapper mapper;

    public ResponseEntity<?> save(RoomTypeDto dto)
    {
        RoomType roomType = mapper.toEntity(dto);
        roomTypeService.save(roomType);
        return ResponseEntity.ok(mapper.toDto(roomType));

    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        List<RoomType> roomTypes = roomTypeService.getAll(page, size);
        Page<RoomType> pages = new PageImpl<RoomType>(roomTypes, PageRequest.of(page, size), roomTypes.size());
        PaginatedResultDto<RoomTypeDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(mapper.toDto(pages.getContent()));
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(pages));

        return ResponseEntity.ok(paginatedResultDto);
    }


    public ResponseEntity<?> getById(Integer id)
    {
        Optional<RoomType> roomType = roomTypeService.getById(id);
        return ResponseEntity.ok(roomType);
    }

    public ResponseEntity<?> update(Integer id, RoomTypeDto roomTypeDto)
    {

        RoomType roomType=roomTypeService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(RoomType.class.getSimpleName(),id));
        mapper.updateEntityFromDto(roomTypeDto, roomType);
        roomTypeService.update(roomType);
        RoomTypeDto dto = mapper.toDto(roomType);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        RoomType roomType = roomTypeService.getById(id).orElseThrow(() -> new ResourceNotFoundException(RoomType.class.getSimpleName(), id));
        try
        {
            roomTypeService.delete(roomType);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(RoomType.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));

    }
}
