package com.cityfashionpos.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.SalesTransactionEntity;
import com.cityfashionpos.model.PaymentStatus;
import com.cityfashionpos.model.TransactionType;

@Repository
public interface SalesTransactionRepository extends JpaRepository<SalesTransactionEntity, Long> {

        /**
         * Find by transaction number
         */
        Optional<SalesTransactionEntity> findByTransactionNumber(String transactionNumber);

        /**
         * Find all transactions within date range
         */
        @Query("SELECT st FROM SalesTransactionEntity st WHERE st.transactionDate BETWEEN :startDate AND :endDate ORDER BY st.transactionDate DESC, st.transactionTime DESC")
        List<SalesTransactionEntity> findByTransactionDateBetween(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Find all transactions for current month
         */
        @Query("SELECT st FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) AND MONTH(st.transactionDate) = MONTH(CURRENT_DATE) ORDER BY st.transactionDate DESC, st.transactionTime DESC")
        List<SalesTransactionEntity> findCurrentMonthTransactions();

        /**
         * Find all transactions for last month
         */
        @Query(value = "SELECT * FROM sales_transactions st WHERE YEAR(st.transaction_date) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND MONTH(st.transaction_date) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) ORDER BY st.transaction_date DESC, st.transaction_time DESC", nativeQuery = true)
        List<SalesTransactionEntity> findLastMonthTransactions();

        /**
         * Find all transactions for current quarter
         */
        @Query("SELECT st FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) AND QUARTER(st.transactionDate) = QUARTER(CURRENT_DATE) ORDER BY st.transactionDate DESC, st.transactionTime DESC")
        List<SalesTransactionEntity> findCurrentQuarterTransactions();

        /**
         * Find all transactions for current year
         */
        @Query("SELECT st FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) ORDER BY st.transactionDate DESC, st.transactionTime DESC")
        List<SalesTransactionEntity> findCurrentYearTransactions();

        /**
         * Find transactions by customer
         */
        List<SalesTransactionEntity> findByCustomerId(Long customerId);

        /**
         * Find transactions by payment status
         */
        List<SalesTransactionEntity> findByPaymentStatus(PaymentStatus paymentStatus);

        /**
         * Find transactions by transaction type
         */
        List<SalesTransactionEntity> findByTransactionType(TransactionType transactionType);

        /**
         * Find transactions by invoice ID
         */
        Optional<SalesTransactionEntity> findByInvoiceId(Long invoiceId);

        /**
         * Get maximum transaction ID for generating transaction numbers
         */
        @Query("SELECT MAX(st.id) FROM SalesTransactionEntity st")
        Long findMaxTransactionId();

        /**
         * Calculate total sales amount for current month
         */
        @Query("SELECT COALESCE(SUM(st.netAmount), 0) FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) AND MONTH(st.transactionDate) = MONTH(CURRENT_DATE) AND st.transactionType = 'SALE' AND st.status = 'COMPLETED'")
        BigDecimal getCurrentMonthTotalSales();

        /**
         * Calculate total sales amount for last month
         */
        @Query(value = "SELECT COALESCE(SUM(st.net_amount), 0) FROM sales_transactions st WHERE YEAR(st.transaction_date) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND MONTH(st.transaction_date) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND st.transaction_type = 'SALE' AND st.status = 'COMPLETED'", nativeQuery = true)
        BigDecimal getLastMonthTotalSales();

        /**
         * Calculate total received amount for current month
         */
        @Query("SELECT COALESCE(SUM(st.receivedAmount), 0) FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) AND MONTH(st.transactionDate) = MONTH(CURRENT_DATE) AND st.transactionType = 'SALE' AND st.status = 'COMPLETED'")
        BigDecimal getCurrentMonthTotalReceived();

        /**
         * Calculate total balance amount for current month
         */
        @Query("SELECT COALESCE(SUM(st.balanceAmount), 0) FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) AND MONTH(st.transactionDate) = MONTH(CURRENT_DATE) AND st.transactionType = 'SALE' AND st.status = 'COMPLETED'")
        BigDecimal getCurrentMonthTotalBalance();

        /**
         * Calculate total sales amount for date range
         */
        @Query("SELECT COALESCE(SUM(st.netAmount), 0) FROM SalesTransactionEntity st WHERE st.transactionDate BETWEEN :startDate AND :endDate AND st.transactionType = 'SALE' AND st.status = 'COMPLETED'")
        BigDecimal getTotalSalesForDateRange(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Calculate total received amount for date range
         */
        @Query("SELECT COALESCE(SUM(st.receivedAmount), 0) FROM SalesTransactionEntity st WHERE st.transactionDate BETWEEN :startDate AND :endDate AND st.transactionType = 'SALE' AND st.status = 'COMPLETED'")
        BigDecimal getTotalReceivedForDateRange(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Calculate total balance amount for date range
         */
        @Query("SELECT COALESCE(SUM(st.balanceAmount), 0) FROM SalesTransactionEntity st WHERE st.transactionDate BETWEEN :startDate AND :endDate AND st.transactionType = 'SALE' AND st.status = 'COMPLETED'")
        BigDecimal getTotalBalanceForDateRange(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Get transaction count for current month
         */
        @Query("SELECT COUNT(st) FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) AND MONTH(st.transactionDate) = MONTH(CURRENT_DATE) AND st.transactionType = 'SALE' AND st.status = 'COMPLETED'")
        Long getCurrentMonthTransactionCount();

        /**
         * Get transaction count for last month
         */
        @Query(value = "SELECT COUNT(st.id) FROM sales_transactions st WHERE YEAR(st.transaction_date) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND MONTH(st.transaction_date) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND st.transaction_type = 'SALE' AND st.status = 'COMPLETED'", nativeQuery = true)
        Long getLastMonthTransactionCount();

        /**
         * Find transactions with outstanding balance
         */
        @Query("SELECT st FROM SalesTransactionEntity st WHERE st.balanceAmount > 0 AND st.status = 'COMPLETED' ORDER BY st.transactionDate DESC")
        List<SalesTransactionEntity> findTransactionsWithOutstandingBalance();

        /**
         * Find top customers by total purchase amount
         */
        @Query("SELECT st.customerId, st.customerName, SUM(st.netAmount) as totalAmount FROM SalesTransactionEntity st WHERE st.transactionType = 'SALE' AND st.status = 'COMPLETED' GROUP BY st.customerId, st.customerName ORDER BY totalAmount DESC")
        List<Object[]> findTopCustomersByAmount();

        /**
         * Get daily sales summary for a date range
         */
        @Query("SELECT st.transactionDate, COUNT(st), SUM(st.netAmount), SUM(st.receivedAmount), SUM(st.balanceAmount) FROM SalesTransactionEntity st WHERE st.transactionDate BETWEEN :startDate AND :endDate AND st.transactionType = 'SALE' AND st.status = 'COMPLETED' GROUP BY st.transactionDate ORDER BY st.transactionDate DESC")
        List<Object[]> getDailySalesSummary(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        /**
         * Get monthly sales summary for current year
         */
        @Query("SELECT MONTH(st.transactionDate), YEAR(st.transactionDate), COUNT(st), SUM(st.netAmount), SUM(st.receivedAmount), SUM(st.balanceAmount) FROM SalesTransactionEntity st WHERE YEAR(st.transactionDate) = YEAR(CURRENT_DATE) AND st.transactionType = 'SALE' AND st.status = 'COMPLETED' GROUP BY MONTH(st.transactionDate), YEAR(st.transactionDate) ORDER BY MONTH(st.transactionDate)")
        List<Object[]> getMonthlySalesSummaryForCurrentYear();

        /**
         * Check if transaction number exists
         */
        boolean existsByTransactionNumber(String transactionNumber);

        /**
         * Find transactions by status
         */
        List<SalesTransactionEntity> findByStatus(String status);

        /**
         * Find transactions created between dates
         */
        List<SalesTransactionEntity> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

        /**
         * Find transactions by sales person
         */
        List<SalesTransactionEntity> findBySalesPersonId(Long salesPersonId);

        /**
         * Calculate total received amount across all transactions
         */
        @Query("SELECT COALESCE(SUM(st.receivedAmount), 0) FROM SalesTransactionEntity st WHERE st.status = 'COMPLETED'")
        BigDecimal getTotalReceivedAmount();

        /**
         * Calculate total balance amount across all transactions
         */
        @Query("SELECT COALESCE(SUM(st.balanceAmount), 0) FROM SalesTransactionEntity st WHERE st.status = 'COMPLETED'")
        BigDecimal getTotalBalanceAmount();

        /**
         * Calculate total received amount for last month
         */
        // @Query(value = "SELECT COALESCE(SUM(st.received_amount), 0) FROM
        // sales_transactions st WHERE YEAR(st.transaction_date) =
        // YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND MONTH(st.transaction_date)
        // = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND st.transaction_type =
        // 'SALE' AND st.status = 'COMPLETED'", nativeQuery = true)
        // @Query(value = """
        // SELECT COALESCE(SUM(st.received_amount), 0)
        // FROM sales_transactions st
        // WHERE st.transaction_date >= DATE_SUB(CURDATE(), INTERVAL DAY(CURDATE()) DAY)
        // AND st.transaction_date < CURDATE()
        // AND st.transaction_type = 'SALE'
        // AND st.status = 'COMPLETED'
        // """, nativeQuery = true)
        // BigDecimal getLastMonthTotalReceived();

        // @Query(value = """
        // SELECT COALESCE(SUM(st.balance_amount), 0)
        // FROM sales_transactions st
        // WHERE st.transaction_date >= DATE_SUB(CURDATE(), INTERVAL DAY(CURDATE()) DAY)
        // AND st.transaction_date < CURDATE()
        // AND st.transaction_type = 'SALE'
        // AND st.status = 'COMPLETED'
        // """, nativeQuery = true)
        // BigDecimal getLastMonthTotalBalance();
        @Query(value = """
                        SELECT COALESCE(SUM(st.received_amount), 0)
                        FROM sales_transactions st
                        WHERE st.transaction_date BETWEEN :startDate AND :endDate
                          AND st.transaction_type = 'SALE'
                          AND st.status = 'COMPLETED'
                        """, nativeQuery = true)
        BigDecimal getLastMonthTotalReceived(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Query(value = """
                        SELECT COALESCE(SUM(st.balance_amount), 0)
                        FROM sales_transactions st
                        WHERE st.transaction_date BETWEEN :startDate AND :endDate
                          AND st.transaction_type = 'SALE'
                          AND st.status = 'COMPLETED'
                        """, nativeQuery = true)
        BigDecimal getLastMonthTotalBalance(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);
}
