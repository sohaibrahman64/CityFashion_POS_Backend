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

import com.cityfashionpos.dto.SalesOrderTransactionRequest;
import com.cityfashionpos.dto.SalesOrderTransactionResponse;
import com.cityfashionpos.service.SalesOrderTransactionService;

@RestController
@RequestMapping("/api/sales-order-transactions")
@CrossOrigin(origins = "*")
public class SalesOrderTransactionsController {
    private static final Logger logger = LoggerFactory.getLogger(SalesOrderTransactionsController.class);

    @Autowired
    private SalesOrderTransactionService salesOrderTransactionService;

    /**
     * Create a new sales transaction
     */
    @PostMapping("/create")
    public ResponseEntity<SalesOrderTransactionResponse> createSalesTransaction(
            @RequestBody SalesOrderTransactionRequest request) {
        try {
            logger.info("Creating sales transaction for amount: {}", request.getTotalAmount());
            SalesOrderTransactionResponse response = salesOrderTransactionService.createSalesOrderTransaction(request);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error creating sales transaction: {}", e.getMessage(), e);
            SalesOrderTransactionResponse errorResponse = new SalesOrderTransactionResponse(false,
                    "Error creating sales transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get total received and balance amounts across all transactions
     */
    @GetMapping("/totals")
    public ResponseEntity<Map<String, Object>> getTotalReceivedAndBalanceAmounts(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            Map<String, BigDecimal> totals;

            // If both date parameters are provided, use date range query
            if (fromDate != null && toDate != null) {
                totals = salesOrderTransactionService.getTotalSalesOrderAmountsByDateRange(fromDate, toDate);
            } else {
                // Otherwise, use the original method for all-time totals
                totals = salesOrderTransactionService.getTotalFulfilledAndOverdueAmounts();
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
    public ResponseEntity<List<SalesOrderTransactionResponse>> getSalesOrderRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            logger.info("Fetching sales records for report from {} to {}", fromDate, toDate);
            List<SalesOrderTransactionResponse> salesRecords = salesOrderTransactionService
                    .getSalesOrderTransactionByDateRange(fromDate,
                            toDate);
            return ResponseEntity.ok(salesRecords);
        } catch (Exception e) {
            logger.error("Error fetching sales records for report from {} to {}: {}", fromDate, toDate, e.getMessage(),
                    e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

}
