package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cityfashionpos.dto.ProformaInvoiceTransactionRequest;
import com.cityfashionpos.dto.ProformaInvoiceTransactionResponse;

public interface ProformaInvoiceTransactionService {
        /**
         * Create a new proforma invoice transaction
         */
        ProformaInvoiceTransactionResponse createProformaInvoiceTransaction(
                        ProformaInvoiceTransactionRequest request);

        /**
         * Get all proforma invoice transaction
         */
        List<ProformaInvoiceTransactionResponse> getAllProformaInvoiceTransactions();

        /**
         * Get proforma invoice transaction by transaction number
         */
        String generateProformaInvoiceTransactionNumber();

        /**
         * Get total proforma invoice amount for a date range
         */
        Map<String, BigDecimal> getTotalProformaInvoiceAmountsByDateRange(LocalDate fromDate, LocalDate toDate);

        /**
         * Get total Converted and Open Proforma Invoice amount
         */
        Map<String, BigDecimal> getTotalOpenAndConvertedAmounts();

        /**
         * Calculate percentage change vs last month
         */
        BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount);

        /**
         * Get Proforma Invoice transaction records by date range
         */
        List<ProformaInvoiceTransactionResponse> getProformaInvoiceTransactionByDateRange(LocalDate fromDate,
                        LocalDate toDate);
}
