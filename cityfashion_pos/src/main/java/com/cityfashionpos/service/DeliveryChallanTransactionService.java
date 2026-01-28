package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cityfashionpos.dto.DeliveryChallanTransactionRequest;
import com.cityfashionpos.dto.DeliveryChallanTransactionResponse;

public interface DeliveryChallanTransactionService {
    /**
     * Create a new delivery challan transaction
     */
    DeliveryChallanTransactionResponse createDeliveryChallanTransaction(
            DeliveryChallanTransactionRequest request);

    /**
     * Get all delivery challan transaction
     */
    List<DeliveryChallanTransactionResponse> getAllDeliveryChallanTransactions();

    /**
     * Get delivery challan transaction by transaction number
     */
    String generateDeliveryChallanTransactionNumber();

    /**
     * Get total delivery challan amount for a date range
     */
    Map<String, BigDecimal> getTotalDeliveryChallanAmountsByDateRange(LocalDate fromDate, LocalDate toDate);

    /**
     * Get total Converted and Open Delivery Challan amount
     */
    Map<String, BigDecimal> getTotalOpenAndConvertedAmounts();

    /**
     * Calculate percentage change vs last month
     */
    BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount);

    /**
     * Get Delivery Challan transaction records by date range
     */
    List<DeliveryChallanTransactionResponse> getDeliveryChallanTransactionByDateRange(LocalDate fromDate,
            LocalDate toDate);
}
