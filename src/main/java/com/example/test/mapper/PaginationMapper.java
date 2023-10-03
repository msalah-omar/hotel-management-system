package com.example.test.mapper;


import com.example.test.dto.commen.PaginationDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PaginationMapper {

    public PaginationDto toPaginationDto(Page page) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setCurrentPage(page.getNumber());
        paginationDto.setItemCount(page.getTotalElements());
        paginationDto.setSize(page.getSize());
        paginationDto.setTotalPages(page.getTotalPages());
        return paginationDto;
    }

}
