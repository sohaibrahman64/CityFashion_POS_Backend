package com.cityfashionpos.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.EstimateQuotationTransactionRequest;
import com.cityfashionpos.dto.EstimateQuotationTransactionResponse;
import com.cityfashionpos.dto.SalesReportResponse;
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

    /**
     * Get total estimate quotation amount
     */
    @GetMapping("/totals")
    public ResponseEntity<Map<String, Object>> getTotalEstimateQuotationAmount(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            Map<String, BigDecimal> totals;
            // If both date parameters are provided, use date range query
            if (fromDate != null && toDate != null) {
                totals = estimateQuotationTransactionService.getTotalEstimateQuotationAmountsByDateRange(fromDate,
                        toDate);
            } else {
                // Otherwise, use the original method for all-time totals
                totals = estimateQuotationTransactionService.getTotalOpenAndConvertedAmounts();
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", totals);
            response.put("message", "Totals retrieved successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error fetching total amounts: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error fetching total amounts: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get all estimate quotation transaction records by
     * date range. Returns specific fields: Date, Reference Number,
     * Party Name, Amount, Balance, and Status
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<EstimateQuotationTransactionResponse>> getEstimateTransactionRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            logger.info("Fetching all estimate quotation transaction records from {} to {}", fromDate, toDate);
            List<EstimateQuotationTransactionResponse> estimateQuotationTransactionRecords = estimateQuotationTransactionService
                    .getEstimateQuotationTransactionByDateRange(fromDate, toDate);
            return ResponseEntity.ok(estimateQuotationTransactionRecords);
        } catch (Exception e) {
            logger.error("Error fetching all estimate quotation transaction records from {} to {}: {}", fromDate,
                    toDate, e.getMessage(),
                    e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

}
