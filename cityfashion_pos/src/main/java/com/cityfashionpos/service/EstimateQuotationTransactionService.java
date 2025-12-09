package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cityfashionpos.dto.EstimateQuotationTransactionRequest;
import com.cityfashionpos.dto.EstimateQuotationTransactionResponse;

public interface EstimateQuotationTransactionService {

    /**
     * Create a new estimate quotation transaction
     */
    EstimateQuotationTransactionResponse createEstimateQuotationTransaction(
            EstimateQuotationTransactionRequest request);

    /**
     * Get all sales transaction
     */
    List<EstimateQuotationTransactionResponse> getAllEstimateQuotationTransactions();

    /**
     * Get estimate quotation transaction by transaction number
     */
    String generateEstimateQuotationTransactionNumber();

    /**
     * Get total estimate quotation amount for a date range
     */
    Map<String, BigDecimal> getTotalEstimateQuotationAmountsByDateRange(LocalDate fromDate, LocalDate toDate);

    /**
     * Get total Converted and Open Estimate Quotation amount
     */
    Map<String, BigDecimal> getTotalOpenAndConvertedAmounts();

    /**
     * Calculate percentage change vs last month
     */
    BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount);

    /**
     * Get Estimate Quotation transaction records by date range
     */
    List<EstimateQuotationTransactionResponse> getEstimateQuotationTransactionByDateRange(LocalDate fromDate,
            LocalDate toDate);

}
