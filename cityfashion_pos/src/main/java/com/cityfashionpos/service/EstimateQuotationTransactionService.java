package com.cityfashionpos.service;

import java.util.List;
import java.util.Optional;

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

}
