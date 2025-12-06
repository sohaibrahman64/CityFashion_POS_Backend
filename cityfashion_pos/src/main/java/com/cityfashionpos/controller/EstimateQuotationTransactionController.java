package com.cityfashionpos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.EstimateQuotationTransactionRequest;
import com.cityfashionpos.dto.EstimateQuotationTransactionResponse;
import com.cityfashionpos.service.EstimateQuotationTransactionService;

@RestController
@RequestMapping("/api/estimate-quotation-transactions")
@CrossOrigin(origins = "*")
public class EstimateQuotationTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(EstimateQuotationTransactionController.class);

    @Autowired
    private EstimateQuotationTransactionService estimateQuotationTransactionService;

    /**
     * Create a new estimate quotation transaction
     */
    @PostMapping("/create")
    public ResponseEntity<EstimateQuotationTransactionResponse> createEstimateQuotationTransaction(
            @RequestBody EstimateQuotationTransactionRequest request) {
        try {
            logger.info("Creating Estimate Quotation transaction for amount: {}", request.getTotalAmount());
            EstimateQuotationTransactionResponse response = estimateQuotationTransactionService
                    .createEstimateQuotationTransaction(request);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error creating sales transaction: {}", e.getMessage(), e);
            EstimateQuotationTransactionResponse errorResponse = new EstimateQuotationTransactionResponse(false,
                    "Error creating sales transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
