package com.cityfashionpos.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.ProformaInvoiceTransactionEntity;

@Repository
public interface ProformaInvoiceTransactionRepository extends JpaRepository<ProformaInvoiceTransactionEntity, Long> {
        /**
         * Get maximum transaction ID for generating transaction numbers
         */
        @Query("SELECT MAX(pit.id) FROM ProformaInvoiceTransactionEntity pit")
        Long findMaxTransactionId();

        /**
         * Calculate total estimate quotation amount for a date range of current month
         */

        @Query("SELECT COALESCE(SUM(pit.totalAmount), 0) FROM ProformaInvoiceTransactionEntity pit WHERE pit.transactionDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalProformaInvoiceAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /**
         * Calculate open estimate quotation amount for a date range of current month
         */
        @Query("SELECT COALESCE(SUM(pit.balanceAmount), 0) FROM ProformaInvoiceTransactionEntity pit WHERE pit.transactionDate BETWEEN :startDate AND :endDate AND pit.status = 'OPEN'")
        BigDecimal getTotalOpenAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /**
         * Calculate converted estimate quotation amount for a date range of current
         * month
         */
        @Query("SELECT COALESCE(SUM(pit.totalAmount), 0) FROM ProformaInvoiceTransactionEntity pit WHERE pit.transactionDate BETWEEN :startDate AND :endDate AND pit.status = 'CONVERTED'")
        BigDecimal getTotalConvertedAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /*
         * Calculate total open estimate quotation amount for current month
         */
        @Query("SELECT COALESCE(SUM(pit.totalAmount), 0) FROM ProformaInvoiceTransactionEntity pit WHERE pit.status = 'OPEN'")
        BigDecimal getTotalOpenAmount();

        /**
         * Calculate total converted estimate quotation amount for current month
         * 
         */

        @Query("SELECT COALESCE(SUM(pit.totalAmount), 0) FROM ProformaInvoiceTransactionEntity pit WHERE pit.status = 'CONVERTED'")
        BigDecimal getTotalConvertedAmount();

        /*
         * Calculate total open estimate quotation amount for the last month
         */
        @Query(value = """
                        SELECT COALESCE(SUM(pit.total_amount), 0)
                        FROM proforma_invoice_transactions pit
                        WHERE pit.transaction_date BETWEEN :startDate AND :endDate
                          AND eqt.status = 'OPEN'
                        """, nativeQuery = true)
        BigDecimal getLastMonthTotalOpenAmount(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /*
         * Calculate total converted estimate quotation amount for the last month
         */
        @Query(value = """
                        SELECT COALESCE(SUM(pit.total_amount), 0)
                        FROM proforma_invoice_transactions pit
                        WHERE pit.transaction_date BETWEEN :startDate AND :endDate
                          AND pit.status = 'CONVERTED'
                        """, nativeQuery = true)
        BigDecimal getLastMonthTotalConvertedAmount(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /**
         * Find all transactions within date range
         */
        @Query("SELECT pit FROM ProformaInvoiceTransactionEntity pit WHERE pit.transactionDate BETWEEN :startDate AND :endDate ORDER BY pit.transactionDate DESC, pit.transactionTime DESC")
        List<ProformaInvoiceTransactionEntity> findByTransactionDateBetween(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

}
