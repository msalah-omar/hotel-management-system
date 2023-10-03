package com.example.test.mapper;

import com.example.test.dto.BookingDto;
import com.example.test.dto.InvoiceDto;
import com.example.test.entity.Booking;
import com.example.test.entity.Invoice;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper
{
    InvoiceDto toDto(Invoice baseEntityPram);
    Invoice toEntity(InvoiceDto baseDtoPram);

    List<InvoiceDto> toDto(List<Invoice> baseEntityPram);
    List<Invoice> toEntity(List<InvoiceDto> baseDtoPram);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Invoice updateEntityFromDto (InvoiceDto invoiceDto, @MappingTarget Invoice invoice);
}
