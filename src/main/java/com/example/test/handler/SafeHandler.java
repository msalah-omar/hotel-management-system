package com.example.test.handler;

import com.example.test.dto.Response;
import com.example.test.dto.SafeDto;
import com.example.test.dto.commen.PaginatedResultDto;
import com.example.test.entity.*;
import com.example.test.exception.ErrorCodes;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.exception.ResourceRelatedException;
import com.example.test.mapper.PaginationMapper;
import com.example.test.mapper.SafeMapper;
import com.example.test.service.HotelService;
import com.example.test.service.SafeService;
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
public class SafeHandler
{
    private SafeService safeService;
    private PaginationMapper paginationMapper;
    private SafeMapper safeMapper;
    private HotelService hotelService;

    public ResponseEntity<?> save(SafeDto dto)
    {
        Safe safe = safeMapper.toEntity(dto);
//        safe.setCacheIn(0);
//        safe.setCacheOut(0);
        safe.setHotel(hotelService.getById(dto.getHotel().getId()).get());
        safeService.save(safe);
        return ResponseEntity.ok(safeMapper.toDto(safe));

    }

    public ResponseEntity<?> getShowSafeCurrentBalance(Integer hotelId)
    {
        Safe safe = safeService.getById(hotelId).
                orElseThrow(() -> new ResourceNotFoundException(Safe.class.getSimpleName(), hotelId));
        Double showSafeCurrentBalance = safeService.getShowSafeCurrentBalance(hotelId);
        return ResponseEntity.ok(new Response("showSafeCurrentBalance Is " + showSafeCurrentBalance));
    }


    public ResponseEntity<?> getAll(Integer page, Integer size)
    {
        List<Safe> safes = safeService.getAll(page, size);
        Page<Safe> pages = new PageImpl<Safe>(safes, PageRequest.of(page, size), safes.size());
        PaginatedResultDto<SafeDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(safeMapper.toDto(pages.getContent()));
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(pages));

        return ResponseEntity.ok(paginatedResultDto);
    }


    public ResponseEntity<?> getById(Integer id)
    {
        Optional<Safe> safe = safeService.getById(id);
        return ResponseEntity.ok(safe);
    }

    public ResponseEntity<?> update(Integer id, SafeDto safeDto)
    {

        Safe safe=safeService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Safe.class.getSimpleName(),id));
        safeMapper.updateEntityFromDto(safeDto,safe);
        safeService.update(safe);
        SafeDto dto = safeMapper.toDto(safe);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id)
    {

        Safe safe = safeService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Safe.class.getSimpleName(), id));
        try
        {
            safeService.delete(safe);
        } catch (Exception exception)
        {
            throw new ResourceRelatedException(Safe.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
