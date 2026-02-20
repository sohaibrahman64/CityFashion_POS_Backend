package com.cityfashionpos.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cityfashionpos.entity.SalesReturnTransactionEntity;

@Repository
public interface SalesReturnTransactionRepository extends JpaRepository<SalesReturnTransactionEntity, Long> {
        @Query("SELECT MAX(srt.id) FROM SalesReturnTransactionEntity srt")
        Long findMaxTransactionId();

        @Query("SELECT SUM(srt.paidAmount) FROM SalesReturnTransactionEntity srt WHERE srt.transactionDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalPaidAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT SUM(srt.balanceAmount) FROM SalesReturnTransactionEntity srt WHERE srt.transactionDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalBalanceAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT SUM(srt.totalAmount) FROM SalesReturnTransactionEntity srt WHERE srt.transactionDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalSalesReturnAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT srt FROM SalesReturnTransactionEntity srt WHERE srt.transactionDate BETWEEN :startDate AND :endDate ORDER BY srt.transactionDate DESC")
        List<SalesReturnTransactionEntity> findByTransactionDateBetween(String startDate, String endDate);

        @Query("SELECT SUM(srt.balanceAmount) FROM SalesReturnTransactionEntity srt")
        BigDecimal getTotalBalanceAmount();

        @Query("SELECT SUM(srt.paidAmount) FROM SalesReturnTransactionEntity srt")
        BigDecimal getTotalPaidAmount();

        @Query("SELECT SUM(srt.balanceAmount) FROM SalesReturnTransactionEntity srt WHERE srt.transactionDate BETWEEN :startDate AND :endDate")
        BigDecimal getLastMonthTotalBalanceAmount(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT SUM(srt.paidAmount) FROM SalesReturnTransactionEntity srt WHERE srt.transactionDate BETWEEN :startDate AND :endDate")
        BigDecimal getLastMonthTotalPaidAmount(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

}
