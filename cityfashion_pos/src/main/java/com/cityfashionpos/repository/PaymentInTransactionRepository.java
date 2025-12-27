package com.cityfashionpos.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PaymentInTransactionEntity;

@Repository
public interface PaymentInTransactionRepository extends JpaRepository<PaymentInTransactionEntity, Long> {

        /**
         * Get maximum transaction ID for generating transaction numbers
         */
        @Query("SELECT MAX(pit.id) FROM PaymentInTransactionEntity pit")
        Long findMaxTransactionId();

        /**
         * Calculate total payment in amount for a date range of current month
         */
        @Query("SELECT COALESCE(SUM(pit.receivedAmount), 0) FROM PaymentInTransactionEntity pit WHERE pit.paymentReceivedDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalPaymentInAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /**
         * Calculate total payment in amount for a date range of current month
         */
        @Query("SELECT COALESCE(SUM(pit.receivedAmount), 0) FROM PaymentInTransactionEntity pit WHERE pit.paymentReceivedDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalReceivedAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /**
         * Calculate total converted payment in amount for current month
         * 
         */

        @Query("SELECT COALESCE(SUM(pit.receivedAmount), 0) FROM PaymentInTransactionEntity pit")
        BigDecimal getTotalReceivedAmount();

        /*
         * Calculate total received payment in amount for the last month
         */
        @Query(value = """
                        SELECT COALESCE(SUM(pit.received_amount), 0)
                        FROM payment_in_transactions pit
                        WHERE pit.payment_received_date BETWEEN :startDate AND :endDate
                        """, nativeQuery = true)
        BigDecimal getLastMonthTotalReceivedAmount(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        /**
         * Find all transactions within date range
         */
        @Query("SELECT pit FROM PaymentInTransactionEntity pit WHERE pit.paymentReceivedDate BETWEEN :startDate AND :endDate ORDER BY pit.paymentReceivedDate DESC")
        List<PaymentInTransactionEntity> findByPaymentReceivedDateBetween(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

}
