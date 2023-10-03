package com.example.test.controller;


import com.example.test.dto.InvoiceDto;

import com.example.test.handler.InvoiceHandler;
import com.example.test.validation.InsertValidation;
import com.example.test.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Invoice")
@AllArgsConstructor
@RestController
@RequestMapping("/invoice")
public class InvoiceController
{
    private InvoiceHandler invoiceHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all customers")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page" , defaultValue = "0") Integer page ,
            @RequestParam (value = "size" , defaultValue = "10") Integer size)
    {
        return invoiceHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get customer by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id)
    {
        return invoiceHandler.getById(id);
    }

    @GetMapping("/{id}/installments")
    @Operation(summary = "Get All Installments", description = "this api for get all installments of Customer")
    public ResponseEntity<?> getPaymentInstallments(@PathVariable("id") Integer id)
    {
        return invoiceHandler.getById(id);
    }

    @GetMapping("/invoice-booking")
    public ResponseEntity<?> getAllIBookingInvoices(@RequestParam(value = "id") Integer id) {
        return invoiceHandler.getAllIBookingInvoices(id);
    }



    @PostMapping
    @Operation(summary = "Add", description = "this api for add new customer")
    public ResponseEntity<?> save(@Validated(InsertValidation.class)@RequestBody InvoiceDto payment) {
        return invoiceHandler.save(payment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update payment")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @RequestBody InvoiceDto dto,@PathVariable Integer id) {
        return invoiceHandler.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete payment By Id")
    public ResponseEntity<?> delete(Integer  id) {
        return invoiceHandler.delete(id);
    }

}
