package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cityfashionpos.dto.SalesReturnTransactionRequest;
import com.cityfashionpos.dto.SalesReturnTransactionResponse;

public interface SalesReturnTransactionService {

    /**
     * Create a new sales return transaction
     */
    SalesReturnTransactionResponse createSalesReturnTransaction(SalesReturnTransactionRequest request);

    /**
     * Get all sales return transactions
     */
    List<SalesReturnTransactionResponse> getAllSalesReturnTransactions();

    /**
     * Generate sales return transaction number
     */
    String generateSalesReturnTransactionNumber();

    /**
     * Get total received and balance amounts across all return transactions
     */
    Map<String, BigDecimal> getTotalPaidAndBalanceAmounts();

    /**
     * Get total received and balance amounts for a date range
     */
    Map<String, BigDecimal> getTotalSalesReturnAmountsByDateRange(LocalDate fromDate, LocalDate toDate);

    /**
     * Calculate percentage change vs last month
     */
    BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount);

    /**
     * Get sales return records for reporting by date range
     */
    List<SalesReturnTransactionResponse> getSalesReturnTransactionsByDateRange(LocalDate fromDate, LocalDate toDate);
}
