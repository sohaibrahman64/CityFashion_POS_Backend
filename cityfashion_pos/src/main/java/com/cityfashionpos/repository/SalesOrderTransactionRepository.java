package com.cityfashionpos.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.entity.SalesOrderTransactionEntity;

public interface SalesOrderTransactionRepository extends JpaRepository<SalesOrderTransactionEntity, Long> {

        @Query("SELECT MAX(sot.id) FROM SalesOrderTransactionEntity sot")
        Long findMaxTransactionId();

        @Query("SELECT SUM(sot.balanceAmount) FROM SalesOrderTransactionEntity sot WHERE sot.dueDate BETWEEN :startDate AND :endDate AND sot.balanceAmount > 0")
        BigDecimal getTotalOverdueAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT SUM(sot.totalAmount) FROM SalesOrderTransactionEntity sot WHERE sot.orderDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalSalesOrderAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT SUM(sot.totalAmount) FROM SalesOrderTransactionEntity sot WHERE sot.balanceAmount = 0 AND sot.orderDate BETWEEN :startDate AND :endDate")
        BigDecimal getTotalFulfilledAmountForDateRange(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT SUM(sot.totalAmount) FROM SalesOrderTransactionEntity sot WHERE sot.balanceAmount = 0")
        BigDecimal getTotalFulfilledAmount();

        @Query("SELECT SUM(sot.balanceAmount) FROM SalesOrderTransactionEntity sot WHERE sot.balanceAmount > 0")
        BigDecimal getTotalOverdueAmount();

        @Query("SELECT SUM(sot.balanceAmount) FROM SalesOrderTransactionEntity sot WHERE sot.dueDate BETWEEN :startDate AND :endDate AND sot.balanceAmount > 0")
        BigDecimal getLastMonthTotalOverdueAmount(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT SUM(sot.totalAmount - sot.balanceAmount) FROM SalesOrderTransactionEntity sot WHERE sot.orderDate BETWEEN :startDate AND :endDate")
        BigDecimal getLastMonthTotalFulfilledAmount(@Param("startDate") String startDate,
                        @Param("endDate") String endDate);

        @Query("SELECT st FROM SalesOrderTransactionEntity st WHERE st.orderDate BETWEEN :startDate AND :endDate ORDER BY st.orderDate DESC")
        List<SalesOrderTransactionEntity> findByOrderDateBetween(String startDate, String endDate);

}
