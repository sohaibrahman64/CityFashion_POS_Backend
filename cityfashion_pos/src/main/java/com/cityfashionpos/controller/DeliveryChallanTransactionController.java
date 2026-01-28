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

import com.cityfashionpos.dto.DeliveryChallanTransactionRequest;
import com.cityfashionpos.dto.DeliveryChallanTransactionResponse;
import com.cityfashionpos.service.DeliveryChallanTransactionService;

@RestController
@RequestMapping("/api/delivery-challan-transactions")
@CrossOrigin(origins = "*")
public class DeliveryChallanTransactionController {
    private static final Logger logger = LoggerFactory.getLogger(ProformaInvoiceTransactionController.class);

    @Autowired
    private DeliveryChallanTransactionService deliveryChallanTransactionService;

    /**
     * Create a new delivery challan transaction
     */
    @PostMapping("/create")
    public ResponseEntity<DeliveryChallanTransactionResponse> createDeliveryChallanTransaction(
            @RequestBody DeliveryChallanTransactionRequest request) {
        try {
            logger.info("Creating Delivery Challan transaction for amount: {}", request.getTotalAmount());
            DeliveryChallanTransactionResponse response = deliveryChallanTransactionService
                    .createDeliveryChallanTransaction(request);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error creating delivery challan transaction: {}", e.getMessage(), e);
            DeliveryChallanTransactionResponse errorResponse = new DeliveryChallanTransactionResponse(false,
                    "Error creating delivery challan transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get total delivery challan amount
     */
    @GetMapping("/totals")
    public ResponseEntity<Map<String, Object>> getTotalDeliveryChallanAmount(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            Map<String, BigDecimal> totals;
            // If both date parameters are provided, use date range query
            if (fromDate != null && toDate != null) {
                totals = deliveryChallanTransactionService.getTotalDeliveryChallanAmountsByDateRange(fromDate,
                        toDate);
            } else {
                totals = deliveryChallanTransactionService.getTotalOpenAndConvertedAmounts();
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
     * Get all proforma invoice transaction records by
     * date range. Returns specific fields: Date, Reference Number,
     * Party Name, Amount, Balance, and Status
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<DeliveryChallanTransactionResponse>> getDeliveryChallanRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            logger.info("Fetching all delivery challan transaction records from {} to {}", fromDate, toDate);
            List<DeliveryChallanTransactionResponse> deliveryChallanTransactionRecords = deliveryChallanTransactionService
                    .getDeliveryChallanTransactionByDateRange(fromDate, toDate);
            return ResponseEntity.ok(deliveryChallanTransactionRecords);
        } catch (Exception e) {
            logger.error("Error fetching all delivery challan transaction records from {} to {}: {}", fromDate,
                    toDate, e.getMessage(),
                    e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}
