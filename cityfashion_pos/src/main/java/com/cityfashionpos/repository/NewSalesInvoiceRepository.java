package com.cityfashionpos.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.NewSalesInvoiceEntity;

@Repository
public interface NewSalesInvoiceRepository extends JpaRepository<NewSalesInvoiceEntity, Long> {
    @Query("SELECT MAX(invoice.id) FROM NewSalesInvoiceEntity invoice")
    Long findMaxInvoiceId();

    @Query("SELECT MAX(invoice.invoiceNumber) FROM NewSalesInvoiceEntity invoice")
    String findMaxInvoiceNumber();

    @Query("SELECT MAX(invoice.invoiceDate) FROM NewSalesInvoiceEntity invoice")
    LocalDate findMaxInvoiceDate();

    @Query("SELECT MAX(invoice.partyId) FROM NewSalesInvoiceEntity invoice")
    Long findMaxPartyId();

    @Query("SELECT MAX(invoice.totalAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMaxTotalAmount();

    @Query("SELECT MAX(invoice.receivedAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMaxReceivedAmount();

    @Query("SELECT MAX(invoice.balanceAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMaxBalanceAmount();

    @Query("SELECT MAX(invoice.discountAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMaxDiscountAmount();

    @Query("SELECT MAX(invoice.amountInWords) FROM NewSalesInvoiceEntity invoice")
    String findMaxAmountInWords();

    @Query("SELECT MAX(invoice.message) FROM NewSalesInvoiceEntity invoice")
    String findMaxMessage();

    @Query("SELECT MAX(invoice.success) FROM NewSalesInvoiceEntity invoice")
    Boolean findMaxSuccess();

    @Query("SELECT MIN(invoice.invoiceNumber) FROM NewSalesInvoiceEntity invoice")
    String findMinInvoiceNumber();

    @Query("SELECT MIN(invoice.invoiceDate) FROM NewSalesInvoiceEntity invoice")
    LocalDate findMinInvoiceDate();

    @Query("SELECT MIN(invoice.partyId) FROM NewSalesInvoiceEntity invoice")
    Long findMinPartyId();

    @Query("SELECT MIN(invoice.totalAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMinTotalAmount();

    @Query("SELECT MIN(invoice.receivedAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMinReceivedAmount();

    @Query("SELECT MIN(invoice.balanceAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMinBalanceAmount();

    @Query("SELECT MIN(invoice.discountAmount) FROM NewSalesInvoiceEntity invoice")
    Double findMinDiscountAmount();

    @Query("SELECT MIN(invoice.amountInWords) FROM NewSalesInvoiceEntity invoice")
    String findMinAmountInWords();

    @Query("SELECT MIN(invoice.message) FROM NewSalesInvoiceEntity invoice")
    String findMinMessage();

    @Query("SELECT MIN(invoice.success) FROM NewSalesInvoiceEntity invoice")
    Boolean findMinSuccess();

    @Query("SELECT COUNT(invoice.id) FROM NewSalesInvoiceEntity invoice")
    Long findTotalInvoices();

    @Query("SELECT COUNT(invoice.id) FROM NewSalesInvoiceEntity invoice WHERE invoice.success = true")
    Long findTotalSuccessfulInvoices();

    @Query("SELECT COUNT(invoice.id) FROM NewSalesInvoiceEntity invoice WHERE invoice.success = false")
    Long findTotalFailedInvoices();

    @Query("SELECT COUNT(invoice.id) FROM NewSalesInvoiceEntity invoice WHERE invoice.success = true AND invoice.invoiceDate = :date")
    Long findTotalSuccessfulInvoicesByDate(@Param("date") LocalDate date);

    @Query("SELECT COUNT(invoice.id) FROM NewSalesInvoiceEntity invoice WHERE invoice.success = false AND invoice.invoiceDate = :date")
    Long findTotalFailedInvoicesByDate(@Param("date") LocalDate date);
}
