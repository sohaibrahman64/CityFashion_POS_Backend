package com.cityfashionpos.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.EstimateQuotationTransactionEntity;

@Repository
public interface EstimateQuotationTransactionRepository
                extends JpaRepository<EstimateQuotationTransactionEntity, Long> {

        /**
         * Get maximum transaction ID for generating transaction numbers
         */
        @Query("SELECT MAX(eqt.id) FROM EstimateQuotationTransactionEntity eqt")
        Long findMaxTransactionId();

        /**
         * Calculate total estimate quotation amount for a date range of current month
         */

        @Query("SELECT COALESCE(SUM(eqt.totalAmount), 0) FROM EstimateQuotationTransactionEntity eqt WHERE eqt.transactionDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalEstimateQuotationAmountForDateRange(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Calculate open estimate quotation amount for a date range of current month
         */
        @Query("SELECT COALESCE(SUM(eqt.balanceAmount), 0) FROM EstimateQuotationTransactionEntity eqt WHERE eqt.transactionDate BETWEEN :startDate AND :endDate AND eqt.status = 'OPEN'")
        BigDecimal getTotalOpenAmountForDateRange(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Calculate converted estimate quotation amount for a date range of current
         * month
         */
        @Query("SELECT COALESCE(SUM(eqt.totalAmount), 0) FROM EstimateQuotationTransactionEntity eqt WHERE eqt.transactionDate BETWEEN :startDate AND :endDate AND eqt.status = 'CONVERTED'")
        BigDecimal getTotalConvertedAmountForDateRange(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /*
         * Calculate total open estimate quotation amount for current month
         */
        @Query("SELECT COALESCE(SUM(eqt.totalAmount), 0) FROM EstimateQuotationTransactionEntity eqt WHERE eqt.status = 'OPEN'")
        BigDecimal getTotalOpenAmount();

        /**
         * Calculate total converted estimate quotation amount for current month
         * 
         */

        @Query("SELECT COALESCE(SUM(eqt.totalAmount), 0) FROM EstimateQuotationTransactionEntity eqt WHERE eqt.status = 'CONVERTED'")
        BigDecimal getTotalConvertedAmount();

        /*
         * Calculate total open estimate quotation amount for the last month
         */
        @Query(value = """
                        SELECT COALESCE(SUM(eqt.total_amount), 0)
                        FROM estimate_quotation_transactions eqt
                        WHERE eqt.transaction_date BETWEEN :startDate AND :endDate
                          AND eqt.status = 'OPEN'
                        """, nativeQuery = true)
        BigDecimal getLastMonthTotalOpenAmount(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /*
         * Calculate total converted estimate quotation amount for the last month
         */
        @Query(value = """
                        SELECT COALESCE(SUM(eqt.total_amount), 0)
                        FROM estimate_quotation_transactions eqt
                        WHERE eqt.transaction_date BETWEEN :startDate AND :endDate
                          AND eqt.status = 'CONVERTED'
                        """, nativeQuery = true)
        BigDecimal getLastMonthTotalConvertedAmount(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Find all transactions within date range
         */
        @Query("SELECT eqt FROM EstimateQuotationTransactionEntity eqt WHERE eqt.transactionDate BETWEEN :startDate AND :endDate ORDER BY eqt.transactionDate DESC, eqt.transactionTime DESC")
        List<EstimateQuotationTransactionEntity> findByTransactionDateBetween(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

}
