package com.cityfashionpos.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.NewSalesReturnEntity;

@Repository
public interface NewSalesReturnRepository extends JpaRepository<NewSalesReturnEntity, Long> {
    @Query("SELECT MAX(salesReturn.id) FROM NewSalesReturnEntity salesReturn")
    Long findMaxSalesReturnId();

    @Query("SELECT MAX(salesReturn.salesReturnNumber) FROM NewSalesReturnEntity salesReturn")
    String findMaxSalesReturnNumber();

    @Query("SELECT MAX(salesReturn.salesReturnDate) FROM NewSalesReturnEntity salesReturn")
    LocalDate findMaxSalesReturnDate();

    @Query("SELECT MAX(salesReturn.partyId) FROM NewSalesReturnEntity salesReturn")
    Long findMaxPartyId();

    @Query("SELECT MAX(salesReturn.totalAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMaxTotalAmount();

    @Query("SELECT MAX(salesReturn.paidAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMaxPaidAmount();

    @Query("SELECT MAX(salesReturn.balanceAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMaxBalanceAmount();

    @Query("SELECT MAX(salesReturn.discountAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMaxDiscountAmount();

    @Query("SELECT MAX(salesReturn.amountInWords) FROM NewSalesReturnEntity salesReturn")
    String findMaxAmountInWords();

    @Query("SELECT MAX(salesReturn.message) FROM NewSalesReturnEntity salesReturn")
    String findMaxMessage();

    @Query("SELECT MAX(salesReturn.success) FROM NewSalesReturnEntity salesReturn")
    Boolean findMaxSuccess();

    @Query("SELECT MIN(salesReturn.salesReturnNumber) FROM NewSalesReturnEntity salesReturn")
    String findMinSalesReturnNumber();

    @Query("SELECT MIN(salesReturn.salesReturnDate) FROM NewSalesReturnEntity salesReturn")
    LocalDate findMinSalesReturnDate();

    @Query("SELECT MIN(salesReturn.partyId) FROM NewSalesReturnEntity salesReturn")
    Long findMinPartyId();

    @Query("SELECT MIN(salesReturn.totalAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMinTotalAmount();

    @Query("SELECT MIN(salesReturn.paidAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMinPaidAmount();

    @Query("SELECT MIN(salesReturn.balanceAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMinBalanceAmount();

    @Query("SELECT MIN(salesReturn.discountAmount) FROM NewSalesReturnEntity salesReturn")
    Double findMinDiscountAmount();

    @Query("SELECT MIN(salesReturn.amountInWords) FROM NewSalesReturnEntity salesReturn")
    String findMinAmountInWords();

    @Query("SELECT MIN(salesReturn.message) FROM NewSalesReturnEntity salesReturn")
    String findMinMessage();

    @Query("SELECT MIN(salesReturn.success) FROM NewSalesReturnEntity salesReturn")
    Boolean findMinSuccess();

    @Query("SELECT COUNT(salesReturn.id) FROM NewSalesReturnEntity salesReturn")
    Long findTotalSalesReturns();

    @Query("SELECT COUNT(salesReturn.id) FROM NewSalesReturnEntity salesReturn WHERE salesReturn.success = true")
    Long findTotalSuccessfulSalesReturns();

    @Query("SELECT COUNT(salesReturn.id) FROM NewSalesReturnEntity salesReturn WHERE salesReturn.success = false")
    Long findTotalFailedSalesReturns();

    @Query("SELECT COUNT(salesReturn.id) FROM NewSalesReturnEntity salesReturn WHERE salesReturn.success = true AND salesReturn.salesReturnDate = :date")
    Long findTotalSuccessfulSalesReturnsByDate(@Param("date") LocalDate date);

    @Query("SELECT COUNT(salesReturn.id) FROM NewSalesReturnEntity salesReturn WHERE salesReturn.success = false AND salesReturn.salesReturnDate = :date")
    Long findTotalFailedSalesReturnsByDate(@Param("date") LocalDate date);

}
