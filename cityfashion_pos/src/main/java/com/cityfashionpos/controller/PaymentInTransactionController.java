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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.PaymentInTransactionRequest;
import com.cityfashionpos.dto.PaymentInTransactionResponse;
import com.cityfashionpos.service.PaymentInTransactionService;

@RestController
@RequestMapping("/api/payment-in-transactions")
public class PaymentInTransactionController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentInTransactionController.class);

    @Autowired
    private PaymentInTransactionService paymentInTransactionService;

    /*
     * Create a new payment in transaction
     */
    @PostMapping("/create")
    public ResponseEntity<PaymentInTransactionResponse> createPaymentInTransaction(
            @RequestBody PaymentInTransactionRequest request) {
        try {
            logger.info("Creating Payment In transaction for amount: {}", request.getReceivedAmount());
            PaymentInTransactionResponse response = paymentInTransactionService
                    .createPaymentInTransaction(request);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error creating payment in transaction: {}", e.getMessage(), e);
            PaymentInTransactionResponse errorResponse = new PaymentInTransactionResponse(false,
                    "Error creating payment in transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get total payment in amount
     */
    @GetMapping("/totals")
    public ResponseEntity<Map<String, Object>> getTotalPaymentInAmount(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            Map<String, BigDecimal> totals;
            // If both date parameters are provided, use date range query
            if (fromDate != null && toDate != null) {
                totals = paymentInTransactionService.getTotalPaymentInAmountsByDateRange(fromDate,
                        toDate);
            } else {
                // Otherwise, use the original method for all-time totals
                totals = paymentInTransactionService.getTotalReceivedAmount();
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
     * Get all payment in transaction records by
     * date range. Returns specific fields: Date, Reference Number,
     * Party Name, Amount, Balance, and Status
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<PaymentInTransactionResponse>> getPaymentInRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            logger.info("Fetching all payment in transaction records from {} to {}", fromDate, toDate);
            List<PaymentInTransactionResponse> paymentInTransactionRecords = paymentInTransactionService
                    .getPaymentInTransactionByDateRange(fromDate, toDate);
            return ResponseEntity.ok(paymentInTransactionRecords);
        } catch (Exception e) {
            logger.error("Error fetching all payment in transaction records from {} to {}: {}", fromDate,
                    toDate, e.getMessage(),
                    e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}
