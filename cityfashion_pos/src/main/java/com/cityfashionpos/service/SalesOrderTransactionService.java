package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cityfashionpos.dto.SalesOrderTransactionRequest;
import com.cityfashionpos.dto.SalesOrderTransactionResponse;

public interface SalesOrderTransactionService {
    /**
     * Create a new sales transaction
     */
    SalesOrderTransactionResponse createSalesOrderTransaction(SalesOrderTransactionRequest request);

    /**
     * Get all sales transactions
     */
    List<SalesOrderTransactionResponse> getAllSalesOrderTransactions();

    /**
     * Generate sales order transaction number
     */
    String generateSalesOrderTransactionNumber();

    /**
     * Get total sales, fulfilled and overdue amounts for a date range
     */
    Map<String, BigDecimal> getTotalSalesOrderAmountsByDateRange(LocalDate fromDate, LocalDate toDate);

    /**
     * Get total received and balance amounts across all transactions
     */
    Map<String, BigDecimal> getTotalFulfilledAndOverdueAmounts();

    /**
     * Calculate percentage change vs last month
     */
    BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount);

    /**
     * Get sales order transactions by date range
     */
    List<SalesOrderTransactionResponse> getSalesOrderTransactionByDateRange(LocalDate fromDate, LocalDate toDate);

}
