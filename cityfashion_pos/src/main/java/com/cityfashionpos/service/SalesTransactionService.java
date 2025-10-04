package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cityfashionpos.dto.SalesTransactionRequest;
import com.cityfashionpos.dto.SalesTransactionResponse;
import com.cityfashionpos.dto.SalesTransactionSummaryResponse;

public interface SalesTransactionService {

    /**
     * Create a new sales transaction
     */
    SalesTransactionResponse createSalesTransaction(SalesTransactionRequest request);

    /**
     * Get all sales transactions
     */
    List<SalesTransactionResponse> getAllSalesTransactions();

    /**
     * Get sales transaction by ID
     */
    Optional<SalesTransactionResponse> getSalesTransactionById(Long id);

    /**
     * Get sales transaction by transaction number
     */
    Optional<SalesTransactionResponse> getSalesTransactionByNumber(String transactionNumber);

    /**
     * Get sales transactions for current month
     */
    List<SalesTransactionResponse> getCurrentMonthTransactions();

    /**
     * Get sales transactions for last month
     */
    List<SalesTransactionResponse> getLastMonthTransactions();

    /**
     * Get sales transactions for current quarter
     */
    List<SalesTransactionResponse> getCurrentQuarterTransactions();

    /**
     * Get sales transactions for current year
     */
    List<SalesTransactionResponse> getCurrentYearTransactions();

    /**
     * Get sales transactions for custom date range
     */
    List<SalesTransactionResponse> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Get sales transactions by customer
     */
    List<SalesTransactionResponse> getTransactionsByCustomer(Long customerId);

    /**
     * Get sales summary for current month
     */
    SalesTransactionSummaryResponse getCurrentMonthSummary();

    /**
     * Get sales summary for last month
     */
    SalesTransactionSummaryResponse getLastMonthSummary();

    /**
     * Get sales summary for current quarter
     */
    SalesTransactionSummaryResponse getCurrentQuarterSummary();

    /**
     * Get sales summary for current year
     */
    SalesTransactionSummaryResponse getCurrentYearSummary();

    /**
     * Get sales summary for custom date range
     */
    SalesTransactionSummaryResponse getSummaryByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Update sales transaction
     */
    SalesTransactionResponse updateSalesTransaction(Long id, SalesTransactionRequest request);

    /**
     * Delete sales transaction
     */
    boolean deleteSalesTransaction(Long id);

    /**
     * Generate transaction number
     */
    String generateTransactionNumber();

    /**
     * Create transaction from invoice
     */
    SalesTransactionResponse createTransactionFromInvoice(Long invoiceId);

    /**
     * Get transactions with outstanding balance
     */
    List<SalesTransactionResponse> getTransactionsWithOutstandingBalance();

    /**
     * Calculate percentage change vs last month
     */
    BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount);

    /**
     * Get total received and balance amounts across all transactions
     */
    Map<String, BigDecimal> getTotalReceivedAndBalanceAmounts();

    /**
     * Get total sales, received and balance amounts for a date range
     */
    Map<String, BigDecimal> getTotalAmountsByDateRange(LocalDate fromDate, LocalDate toDate);
}
