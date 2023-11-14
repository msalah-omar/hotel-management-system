package com.example.test.handler;

import com.example.test.dto.RoomDto;
import com.example.test.dto.commen.PaginatedResultDto;
import com.example.test.dto.Response;
import com.example.test.entity.Room;
import com.example.test.exception.ErrorCodes;
import com.example.test.exception.ResourceAlreadyExistsException;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.exception.ResourceRelatedException;
import com.example.test.mapper.PaginationMapper;
import com.example.test.mapper.RoomMapper;
import com.example.test.service.HotelService;
import com.example.test.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoomHandler
{
    private RoomService roomService;
    private PaginationMapper paginationMapper;
    private RoomMapper mapper;
    private HotelService hotelService;

    public ResponseEntity<?> save(RoomDto dto)

    {
        Optional<Room> existedNumber = roomService.findRoomNumber(Integer.valueOf(dto.getNumber()), dto.getHotel().getId());
        if (existedNumber.isPresent())
            throw new ResourceAlreadyExistsException(Room.class.getSimpleName(), "Number", String.valueOf(dto.getNumber()), ErrorCodes.DUPLICATE_RESOURCE.getCode());

        Room room = mapper.toEntity(dto);
        room.setHotel(hotelService.getById(dto.getHotel().getId()).get());
        roomService.save(room);
        return ResponseEntity.ok(mapper.toDto(room));
    }

    public ResponseEntity<?> getByRoomComplete(Boolean complete, Integer page, Integer size)
    {


        Page<Room> rooms = null;
        if (complete == false || complete == null)
        {
            rooms = roomService.getByRoomComplete(complete, page, size);
        } else
        {
            rooms = roomService.getByRoomFull(page, size);

        }

        List<RoomDto> dtos = mapper.toDto(rooms.getContent());
        PaginatedResultDto<RoomDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(rooms));
        return ResponseEntity.ok(paginatedResultDto);
    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        Page<Room> rooms = roomService.getAll(page, size);
        List<RoomDto> dtos = mapper.toDto(rooms.getContent());
        PaginatedResultDto<RoomDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(rooms));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id)
    {
        Optional<Room> room = roomService.getById(id);
        return ResponseEntity.ok(room);
    }


    public ResponseEntity<?> update(RoomDto roomDto, Integer id)

    {
        Optional<Room> existedNumber = roomService.findRoomNumber(Integer.valueOf(roomDto.getNumber()), roomDto.getHotel().getId());
        if (existedNumber.isPresent())
            throw new ResourceAlreadyExistsException(Room.class.getSimpleName(), "Number", String.valueOf(roomDto.getNumber()), ErrorCodes.DUPLICATE_RESOURCE.getCode());

        Room room = roomService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Room.class.getSimpleName(), id));
        mapper.updateEntityFromDto(roomDto, room);
        roomService.update(room);
        RoomDto dto = mapper.toDto(room);
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<?> delete(Integer id)
    {

        Room room = roomService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Room.class.getSimpleName(), id));
        try
        {
            roomService.delete(room);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(Room.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
