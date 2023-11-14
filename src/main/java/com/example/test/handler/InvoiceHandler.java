package com.example.test.handler;


import com.example.test.dto.InvoiceDto;
import com.example.test.dto.SafeDto;
import com.example.test.dto.commen.PaginatedResultDto;

import com.example.test.entity.Booking;
import com.example.test.entity.Invoice;
import com.example.test.dto.Response;
import com.example.test.entity.Safe;
import com.example.test.exception.ErrorCodes;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.exception.ResourceRelatedException;
import com.example.test.mapper.InvoiceMapper;
import com.example.test.mapper.PaginationMapper;

import com.example.test.service.BookingService;
import com.example.test.service.InvoiceService;
import com.example.test.service.SafeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InvoiceHandler
{
    private InvoiceService invoiceService;
    private BookingService bookingService;
    private PaginationMapper paginationMapper;
    private SafeService safeService;

    private InvoiceMapper mapper;

    public ResponseEntity<?> save(InvoiceDto dto)
    {
        Invoice invoice = mapper.toEntity(dto);
        Booking booking = bookingService.getById(dto.getBooking().getId());
        long differenceDates = booking.getFromDate().until(booking.getToDate(), ChronoUnit.DAYS);
        if (differenceDates > 10) {
            Integer price = booking.getRoom().getPrice();
            price = price - (( price * 15 ) / 100);
            invoice.setValue(price);

        }

        invoice.setBooking(booking);
        invoiceService.save(invoice);

        Safe safe = safeService.getByHotelId(invoice.getBooking().getHotel().getId());
        if (safe==null) return ResponseEntity.ok(new Response("not found"));
        Double cacheIn = safe.getCacheIn();
        cacheIn = cacheIn + invoice.getValue();
        safe.setCacheIn(cacheIn);
        safeService.save(safe);

        return ResponseEntity.ok(mapper.toDto(invoice));
    }

    public ResponseEntity<?> getAllIBookingInvoices(Integer userId)
    {
        Invoice invoice1 = invoiceService.getById(userId).
                orElseThrow(() -> new ResourceNotFoundException(Invoice.class.getSimpleName(), userId));
        List<Invoice> invoice = invoiceService.getAllIBookingInvoices(userId);
        List<InvoiceDto> dtos = mapper.toDto(invoice);
        return ResponseEntity.ok(dtos);
    }



    public  ResponseEntity<?> getAll(Integer page , Integer size) {
        Page<Invoice> invoices = invoiceService.getAll(page, size);
        List<InvoiceDto> dtos = mapper.toDto(invoices.getContent());
        PaginatedResultDto<InvoiceDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(invoices));
        return ResponseEntity.ok(paginatedResultDto);}




    public ResponseEntity<?> getById(Integer id)
    {
        Optional<Invoice> invoice = invoiceService.getById(id);
        return ResponseEntity.ok(invoice);
    }


    public ResponseEntity<?> update(Integer id, InvoiceDto invoiceDto)
    {

        Invoice invoice=invoiceService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Booking.class.getSimpleName(),id));

        mapper.updateEntityFromDto(invoiceDto, invoice);
        invoiceService.update(invoice);
        InvoiceDto dto = mapper.toDto(invoice);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?>delete(Integer id){

        Invoice invoice = invoiceService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Invoice.class.getSimpleName(),id));
        try {
            invoiceService.delete(invoice);
        }
        catch (Exception exception)
        {
            throw new ResourceRelatedException(Invoice.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
