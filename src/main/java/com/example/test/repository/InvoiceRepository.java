package com.example.test.repository;

import com.example.test.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice, Integer>
{
    @Query(" select I from Invoice I  where name=:name")
    Invoice findByName (@Param("name") String name);

    @Query(" select i from Invoice i where i.booking.id=:bookingId")
    Invoice findByBookingId (@Param("bookingId") Integer bookingId);


    @Query("select i from Invoice i Join Booking b on b.id = i.booking.id where b.user.id = :userId")
    List<Invoice> getAllIBookingInvoices(@Param("userId") Integer id);

}
