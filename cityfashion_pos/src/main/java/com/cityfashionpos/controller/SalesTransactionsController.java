package com.cityfashionpos.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.SalesReportResponse;
import com.cityfashionpos.dto.SalesTransactionRequest;
import com.cityfashionpos.dto.SalesTransactionResponse;
import com.cityfashionpos.dto.SalesTransactionSummaryResponse;
import com.cityfashionpos.service.SalesTransactionService;

@RestController
@RequestMapping("/api/sales-transactions")
@CrossOrigin(origins = "*")
public class SalesTransactionsController {

    private static final Logger logger = LoggerFactory.getLogger(SalesTransactionsController.class);

    @Autowired
    private SalesTransactionService salesTransactionService;

    /**
     * Create a new sales transaction
     */
    @PostMapping("/create")
    public ResponseEntity<SalesTransactionResponse> createSalesTransaction(
            @RequestBody SalesTransactionRequest request) {
        try {
            logger.info("Creating sales transaction for amount: {}", request.getTotalAmount());
            SalesTransactionResponse response = salesTransactionService.createSalesTransaction(request);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error creating sales transaction: {}", e.getMessage(), e);
            SalesTransactionResponse errorResponse = new SalesTransactionResponse(false,
                    "Error creating sales transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get all sales transactions
     */
    @GetMapping("/all")
    public ResponseEntity<List<SalesTransactionResponse>> getAllSalesTransactions() {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService.getAllSalesTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching all sales transactions: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get sales transaction by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<SalesTransactionResponse> getSalesTransactionById(@PathVariable Long id) {
        try {
            Optional<SalesTransactionResponse> transaction = salesTransactionService.getSalesTransactionById(id);
            if (transaction.isPresent()) {
                return ResponseEntity.ok(transaction.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error fetching sales transaction by ID {}: {}", id, e.getMessage(), e);
            SalesTransactionResponse errorResponse = new SalesTransactionResponse(false,
                    "Error fetching transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get sales transaction by transaction number
     */
    @GetMapping("/number/{transactionNumber}")
    public ResponseEntity<SalesTransactionResponse> getSalesTransactionByNumber(
            @PathVariable String transactionNumber) {
        try {
            Optional<SalesTransactionResponse> transaction = salesTransactionService
                    .getSalesTransactionByNumber(transactionNumber);
            if (transaction.isPresent()) {
                return ResponseEntity.ok(transaction.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error fetching sales transaction by number {}: {}", transactionNumber, e.getMessage(), e);
            SalesTransactionResponse errorResponse = new SalesTransactionResponse(false,
                    "Error fetching transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get sales transactions for current month
     */
    @GetMapping("/current-month")
    public ResponseEntity<List<SalesTransactionResponse>> getCurrentMonthTransactions() {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService.getCurrentMonthTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching current month transactions: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get sales transactions for last month
     */
    @GetMapping("/last-month")
    public ResponseEntity<List<SalesTransactionResponse>> getLastMonthTransactions() {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService.getLastMonthTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching last month transactions: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get sales transactions for current quarter
     */
    @GetMapping("/current-quarter")
    public ResponseEntity<List<SalesTransactionResponse>> getCurrentQuarterTransactions() {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService.getCurrentQuarterTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching current quarter transactions: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get sales transactions for current year
     */
    @GetMapping("/current-year")
    public ResponseEntity<List<SalesTransactionResponse>> getCurrentYearTransactions() {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService.getCurrentYearTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching current year transactions: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get sales transactions for custom date range
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<SalesTransactionResponse>> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService.getTransactionsByDateRange(startDate,
                    endDate);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching transactions by date range: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get sales transactions by customer
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SalesTransactionResponse>> getTransactionsByCustomer(@PathVariable Long customerId) {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService.getTransactionsByCustomer(customerId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching transactions by customer {}: {}", customerId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get sales summary for current month
     */
    @GetMapping("/summary/current-month")
    public ResponseEntity<SalesTransactionSummaryResponse> getCurrentMonthSummary() {
        try {
            SalesTransactionSummaryResponse summary = salesTransactionService.getCurrentMonthSummary();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error fetching current month summary: {}", e.getMessage(), e);
            SalesTransactionSummaryResponse errorResponse = new SalesTransactionSummaryResponse(false,
                    "Error fetching summary: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get sales summary for last month
     */
    @GetMapping("/summary/last-month")
    public ResponseEntity<SalesTransactionSummaryResponse> getLastMonthSummary() {
        try {
            SalesTransactionSummaryResponse summary = salesTransactionService.getLastMonthSummary();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error fetching last month summary: {}", e.getMessage(), e);
            SalesTransactionSummaryResponse errorResponse = new SalesTransactionSummaryResponse(false,
                    "Error fetching summary: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get sales summary for current quarter
     */
    @GetMapping("/summary/current-quarter")
    public ResponseEntity<SalesTransactionSummaryResponse> getCurrentQuarterSummary() {
        try {
            SalesTransactionSummaryResponse summary = salesTransactionService.getCurrentQuarterSummary();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error fetching current quarter summary: {}", e.getMessage(), e);
            SalesTransactionSummaryResponse errorResponse = new SalesTransactionSummaryResponse(false,
                    "Error fetching summary: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get sales summary for current year
     */
    @GetMapping("/summary/current-year")
    public ResponseEntity<SalesTransactionSummaryResponse> getCurrentYearSummary() {
        try {
            SalesTransactionSummaryResponse summary = salesTransactionService.getCurrentYearSummary();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error fetching current year summary: {}", e.getMessage(), e);
            SalesTransactionSummaryResponse errorResponse = new SalesTransactionSummaryResponse(false,
                    "Error fetching summary: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get sales summary for custom date range
     */
    @GetMapping("/summary/date-range")
    public ResponseEntity<SalesTransactionSummaryResponse> getSummaryByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            SalesTransactionSummaryResponse summary = salesTransactionService.getSummaryByDateRange(startDate, endDate);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("Error fetching summary by date range: {}", e.getMessage(), e);
            SalesTransactionSummaryResponse errorResponse = new SalesTransactionSummaryResponse(false,
                    "Error fetching summary: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Update sales transaction
     */
    @PutMapping("/{id}")
    public ResponseEntity<SalesTransactionResponse> updateSalesTransaction(
            @PathVariable Long id,
            @RequestBody SalesTransactionRequest request) {
        try {
            SalesTransactionResponse response = salesTransactionService.updateSalesTransaction(id, request);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error updating sales transaction with ID {}: {}", id, e.getMessage(), e);
            SalesTransactionResponse errorResponse = new SalesTransactionResponse(false,
                    "Error updating sales transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Delete sales transaction
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteSalesTransaction(@PathVariable Long id) {
        try {
            boolean deleted = salesTransactionService.deleteSalesTransaction(id);
            Map<String, Object> response = new HashMap<>();

            if (deleted) {
                response.put("success", true);
                response.put("message", "Sales transaction deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Sales transaction not found with ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error deleting sales transaction with ID {}: {}", id, e.getMessage(), e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error deleting sales transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Generate transaction number
     */
    @GetMapping("/generate-number")
    public ResponseEntity<Map<String, String>> generateTransactionNumber() {
        try {
            String transactionNumber = salesTransactionService.generateTransactionNumber();
            Map<String, String> response = new HashMap<>();
            response.put("transactionNumber", transactionNumber);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error generating transaction number: {}", e.getMessage(), e);
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error generating transaction number: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Create transaction from invoice
     */
    @PostMapping("/create-from-invoice/{invoiceId}")
    public ResponseEntity<SalesTransactionResponse> createTransactionFromInvoice(@PathVariable Long invoiceId) {
        try {
            SalesTransactionResponse response = salesTransactionService.createTransactionFromInvoice(invoiceId);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error("Error creating transaction from invoice ID {}: {}", invoiceId, e.getMessage(), e);
            SalesTransactionResponse errorResponse = new SalesTransactionResponse(false,
                    "Error creating transaction from invoice: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get transactions with outstanding balance
     */
    @GetMapping("/outstanding-balance")
    public ResponseEntity<List<SalesTransactionResponse>> getTransactionsWithOutstandingBalance() {
        try {
            List<SalesTransactionResponse> transactions = salesTransactionService
                    .getTransactionsWithOutstandingBalance();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            logger.error("Error fetching transactions with outstanding balance: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    /**
     * Get comprehensive sales dashboard data
     */
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getSalesDashboard() {
        try {
            Map<String, Object> dashboard = new HashMap<>();

            // Get current month summary
            SalesTransactionSummaryResponse currentMonthSummary = salesTransactionService.getCurrentMonthSummary();
            dashboard.put("currentMonth", currentMonthSummary);

            // Get last month summary for comparison
            SalesTransactionSummaryResponse lastMonthSummary = salesTransactionService.getLastMonthSummary();
            dashboard.put("lastMonth", lastMonthSummary);

            // Get current year summary
            SalesTransactionSummaryResponse currentYearSummary = salesTransactionService.getCurrentYearSummary();
            dashboard.put("currentYear", currentYearSummary);

            // Get outstanding transactions
            List<SalesTransactionResponse> outstandingTransactions = salesTransactionService
                    .getTransactionsWithOutstandingBalance();
            dashboard.put("outstandingTransactions", outstandingTransactions);

            // Add timestamp
            dashboard.put("generatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            logger.error("Error fetching sales dashboard: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error fetching dashboard data: " + e.getMessage());
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
                totals = salesTransactionService.getTotalAmountsByDateRange(fromDate, toDate);
            } else {
                // Otherwise, use the original method for all-time totals
                totals = salesTransactionService.getTotalReceivedAndBalanceAmounts();
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
     * Get sales records for reporting by date range
     * Returns specific fields: Date, Invoice No, Customer Name, Transaction Type,
     * Payment Mode, Total Amount, Balance Amount
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<SalesReportResponse>> getSalesRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            logger.info("Fetching sales records for report from {} to {}", fromDate, toDate);
            List<SalesReportResponse> salesRecords = salesTransactionService.getSalesRecordsByDateRange(fromDate,
                    toDate);
            return ResponseEntity.ok(salesRecords);
        } catch (Exception e) {
            logger.error("Error fetching sales records for report from {} to {}: {}", fromDate, toDate, e.getMessage(),
                    e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}
