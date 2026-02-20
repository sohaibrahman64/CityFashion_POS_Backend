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

import com.cityfashionpos.dto.SalesReturnTransactionRequest;
import com.cityfashionpos.dto.SalesReturnTransactionResponse;
import com.cityfashionpos.service.SalesReturnTransactionService;

@RestController
@RequestMapping("/api/sales-return-transactions")
@CrossOrigin(origins = "*")
public class SalesReturnTransactionsController {

    private static final Logger logger = LoggerFactory.getLogger(SalesReturnTransactionsController.class);

    @Autowired
    private SalesReturnTransactionService salesReturnTransactionService;

    /**
     * Create a new sales return transaction
     */
    @PostMapping("/create")
    public ResponseEntity<SalesReturnTransactionResponse> createSalesReturnTransaction(
            @RequestBody SalesReturnTransactionRequest request) {
        try {
            logger.info("Creating sales return transaction for amount: {}", request.getTotalAmount());
            SalesReturnTransactionResponse response = salesReturnTransactionService
                    .createSalesReturnTransaction(request);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error creating sales return transaction: {}", e.getMessage(), e);
            SalesReturnTransactionResponse errorResponse = new SalesReturnTransactionResponse(false,
                    "Error creating sales return transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get total paid and balance amounts across all transactions
     */
    @GetMapping("/totals")
    public ResponseEntity<Map<String, Object>> getTotalPaidAndBalanceAmounts(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            Map<String, BigDecimal> totals;

            // If both date parameters are provided, use date range query
            if (fromDate != null && toDate != null) {
                totals = salesReturnTransactionService.getTotalSalesReturnAmountsByDateRange(fromDate, toDate);
            } else {
                // Otherwise, use the original method for all-time totals
                totals = salesReturnTransactionService.getTotalPaidAndBalanceAmounts();
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

    @GetMapping("/getAll")
    public ResponseEntity<List<SalesReturnTransactionResponse>> getSalesReturnTransactionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            logger.info("Fetching sales return transactions for report from {} to {}", fromDate, toDate);
            List<SalesReturnTransactionResponse> salesReturnRecords = salesReturnTransactionService
                    .getSalesReturnTransactionsByDateRange(fromDate, toDate);
            return ResponseEntity.ok(salesReturnRecords);
        } catch (Exception e) {
            logger.error("Error fetching sales return transactions for report from {} to {}: {}", fromDate, toDate,
                    e.getMessage(),
                    e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

}
