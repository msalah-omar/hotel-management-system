package com.example.test.service;

import com.example.test.entity.Invoice;
import com.example.test.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceService
{
    private InvoiceRepository invoiceRepository;

    public Page<Invoice> getAll(Integer page, Integer size)
    {
        return invoiceRepository.findAll(PageRequest.of(page,size));
    }

    public List<Invoice> getAllIBookingInvoices(Integer id){
        return  invoiceRepository.getAllIBookingInvoices(id);
    }

    public Optional<Invoice> getById(Integer id )
    {
        return invoiceRepository.findById(id);
    }
    public Invoice getByName(String name)
    {
        return invoiceRepository.findByName(name);
    }
    public Invoice getByBookingId(Integer bookingId)
    {
        return invoiceRepository.findByBookingId(bookingId);
    }

    public Invoice save(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }

    public Invoice update(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> delete(Invoice invoice)
    {
        invoiceRepository.delete(invoice);
        return null;
    }
}
