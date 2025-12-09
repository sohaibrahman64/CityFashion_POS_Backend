package com.cityfashionpos.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.NewEstimateQuotationEntity;

@Repository
public interface NewEstimateQuotationRepository extends JpaRepository<NewEstimateQuotationEntity, Long> {

    @Query("SELECT MAX(e.id) FROM NewEstimateQuotationEntity e")
    Long findMaxEstimateQuotationId();

    @Query("SELECT MAX(e.estimateQuotationNumber) FROM NewEstimateQuotationEntity e")
    String findMaxEstimateQuotationNumber();

    @Query("SELECT MAX(e.estimateQuotationDate) FROM NewEstimateQuotationEntity e")
    LocalDate findMaxEstimateQuotationDate();

    @Query("SELECT MAX(e.partyId) FROM NewEstimateQuotationEntity e")
    Long findMaxPartyId();

    @Query("SELECT MAX(e.totalAmount) FROM NewEstimateQuotationEntity e")
    Double findMaxTotalAmount();

    @Query("SELECT MIN(e.estimateQuotationNumber) FROM NewEstimateQuotationEntity e")
    String findMinEstimateQuotationNumber();

    @Query("SELECT MIN(e.estimateQuotationDate) FROM NewEstimateQuotationEntity e")
    LocalDate findMinEstimateQuotationDate();

    @Query("SELECT MAX(e.discountAmount) FROM NewEstimateQuotationEntity e")
    Double findMaxDiscountAmount();

    @Query("SELECT MAX(e.amountInWords) FROM NewEstimateQuotationEntity e")
    String findMaxAmountInWords();

    @Query("SELECT MAX(e.message) FROM NewEstimateQuotationEntity e")
    String findMaxMessage();

    @Query("SELECT MAX(e.success) FROM NewEstimateQuotationEntity e")
    String findMaxSuccess();

}
