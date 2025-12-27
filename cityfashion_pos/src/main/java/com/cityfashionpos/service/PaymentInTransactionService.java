package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cityfashionpos.dto.PaymentInTransactionRequest;
import com.cityfashionpos.dto.PaymentInTransactionResponse;

public interface PaymentInTransactionService {

    /*
     * Create a new payment in transaction
     */
    PaymentInTransactionResponse createPaymentInTransaction(PaymentInTransactionRequest request);

    /**
     * Get payment in transaction by transaction number
     */
    String generatePaymentInTransactionNumber();

    /*
     * Get total payment in amounts for a date range
     */
    Map<String, BigDecimal> getTotalPaymentInAmountsByDateRange(LocalDate fromDate, LocalDate toDate);

    /**
     * Get total Received Amount
     */
    Map<String, BigDecimal> getTotalReceivedAmount();

    /**
     * Calculate percentage change vs last month
     */
    BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount);

    /**
     * Get Payment In transaction records by date range
     */
    List<PaymentInTransactionResponse> getPaymentInTransactionByDateRange(LocalDate fromDate,
            LocalDate toDate);

}
