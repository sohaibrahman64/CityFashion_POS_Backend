package com.cityfashionpos.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.entity.DeliveryChallanTransactionEntity;

public interface DeliveryChallanTransactionRepository extends JpaRepository<DeliveryChallanTransactionEntity, Long> {
    /**
     * Get maximum transaction ID for generating transaction numbers
     */
    @Query("SELECT MAX(dc.id) FROM DeliveryChallanTransactionEntity dc")
    Long findMaxTransactionId();

    /**
     * Calculate total delivery challan amount for a date range of current month
     */

    @Query("SELECT COALESCE(SUM(dc.totalAmount), 0) FROM DeliveryChallanTransactionEntity dc WHERE dc.transactionDate BETWEEN :startDate AND :endDate")
    BigDecimal getTotalDeliveryChallanAmountForDateRange(@Param("startDate") String startDate,
            @Param("endDate") String endDate);

    /**
     * Calculate open delivery challan amount for a date range of current month
     */
    @Query("SELECT COALESCE(SUM(dc.totalAmount), 0) FROM DeliveryChallanTransactionEntity dc WHERE dc.transactionDate BETWEEN :startDate AND :endDate AND dc.status = 'OPEN'")
    BigDecimal getTotalOpenAmountForDateRange(@Param("startDate") String startDate,
            @Param("endDate") String endDate);

    /**
     * Calculate converted delivery challan amount for a date range of current
     * month
     */
    @Query("SELECT COALESCE(SUM(dc.totalAmount), 0) FROM DeliveryChallanTransactionEntity dc WHERE dc.transactionDate BETWEEN :startDate AND :endDate AND dc.status = 'CONVERTED'")
    BigDecimal getTotalConvertedAmountForDateRange(@Param("startDate") String startDate,
            @Param("endDate") String endDate);

    /*
     * Calculate total open delivery challan amount for current month
     */
    @Query("SELECT COALESCE(SUM(dc.totalAmount), 0) FROM DeliveryChallanTransactionEntity dc WHERE dc.status = 'OPEN'")
    BigDecimal getTotalOpenAmount();

    /**
     * Calculate total converted delivery challan amount for current month
     * 
     */

    @Query("SELECT COALESCE(SUM(dc.totalAmount), 0) FROM DeliveryChallanTransactionEntity dc WHERE dc.status = 'CONVERTED'")
    BigDecimal getTotalConvertedAmount();

    /*
     * Calculate total open delivery challan amount for the last month
     */
    @Query(value = """
            SELECT COALESCE(SUM(dct.total_amount), 0)
            FROM delivery_challan_transactions dct
            WHERE dct.transaction_date BETWEEN :startDate AND :endDate
              AND dct.status = 'OPEN'
            """, nativeQuery = true)
    BigDecimal getLastMonthTotalOpenAmount(@Param("startDate") String startDate,
            @Param("endDate") String endDate);

    /*
     * Calculate total converted delivery challan amount for the last month
     */
    @Query(value = """
            SELECT COALESCE(SUM(dct.total_amount), 0)
            FROM delivery_challan_transactions dct
            WHERE dct.transaction_date BETWEEN :startDate AND :endDate
              AND dct.status = 'CONVERTED'
            """, nativeQuery = true)
    BigDecimal getLastMonthTotalConvertedAmount(@Param("startDate") String startDate,
            @Param("endDate") String endDate);

    /**
     * Find all transactions within date range
     */
    @Query("SELECT dc FROM DeliveryChallanTransactionEntity dc WHERE dc.transactionDate BETWEEN :startDate AND :endDate ORDER BY dc.transactionDate DESC, dc.transactionTime DESC")
    List<DeliveryChallanTransactionEntity> findByTransactionDateBetween(@Param("startDate") String startDate,
            @Param("endDate") String endDate);
}
