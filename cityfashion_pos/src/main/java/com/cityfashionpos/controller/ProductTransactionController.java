package com.cityfashionpos.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.cityfashionpos.dto.ProductTransactionDTO;
import com.cityfashionpos.service.ProductTransactionService;

@RestController
@RequestMapping("/api/product-transactions")
@CrossOrigin(origins = "*")
public class ProductTransactionController {

    @Autowired
    private ProductTransactionService productTransactionService;

    /**
     * Create a new product transaction
     * POST /api/product-transactions
     */
    @PostMapping
    public ResponseEntity<ProductTransactionDTO> createTransaction(@RequestBody ProductTransactionDTO transactionDTO) {
        try {
            ProductTransactionDTO createdTransaction = productTransactionService.createTransaction(transactionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get all transactions for a specific product
     * GET /api/product-transactions/product/{productId}
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductTransactionDTO>> getTransactionsByProduct(@PathVariable Long productId) {
        try {
            List<ProductTransactionDTO> transactions = productTransactionService.getTransactionsByProduct(productId);
            return ResponseEntity.ok(transactions);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get transactions by product and date range
     * GET
     * /api/product-transactions/product/{productId}/date-range?startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/product/{productId}/date-range")
    public ResponseEntity<List<ProductTransactionDTO>> getTransactionsByProductAndDateRange(
            @PathVariable Long productId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate) {
        try {
            List<ProductTransactionDTO> transactions = productTransactionService
                    .getTransactionsByProductAndDateRange(productId, startDate, endDate);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get transactions by type
     * GET /api/product-transactions/type/{transactionType}
     */
    @GetMapping("/type/{transactionType}")
    public ResponseEntity<List<ProductTransactionDTO>> getTransactionsByType(@PathVariable String transactionType) {
        try {
            // Convert string to enum - you might want to add validation here
            com.cityfashionpos.model.TransactionType type = com.cityfashionpos.model.TransactionType
                    .valueOf(transactionType.toUpperCase());
            List<ProductTransactionDTO> transactions = productTransactionService.getTransactionsByType(type);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get transactions by date range
     * GET
     * /api/product-transactions/date-range?startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<ProductTransactionDTO>> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate) {
        try {
            List<ProductTransactionDTO> transactions = productTransactionService.getTransactionsByDateRange(startDate,
                    endDate);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get transaction by ID
     * GET /api/product-transactions/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductTransactionDTO> getTransactionById(@PathVariable Long id) {
        try {
            ProductTransactionDTO transaction = productTransactionService.getTransactionById(id);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update transaction
     * PUT /api/product-transactions/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductTransactionDTO> updateTransaction(
            @PathVariable Long id,
            @RequestBody ProductTransactionDTO transactionDTO) {
        try {
            ProductTransactionDTO updatedTransaction = productTransactionService.updateTransaction(id, transactionDTO);
            return ResponseEntity.ok(updatedTransaction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete transaction
     * DELETE /api/product-transactions/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            productTransactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get transaction summary for a product
     * GET
     * /api/product-transactions/summary/{productId}?startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/summary/{productId}")
    public ResponseEntity<Object> getTransactionSummary(
            @PathVariable Long productId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate) {
        try {
            Object summary = productTransactionService.getTransactionSummary(productId, startDate, endDate);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create transaction from stock adjustment
     * POST /api/product-transactions/stock-adjustment
     */
    @PostMapping("/stock-adjustment")
    public ResponseEntity<ProductTransactionDTO> createFromStockAdjustment(
            @RequestParam Long productId,
            @RequestParam String type,
            @RequestParam Double quantity,
            @RequestParam Double unitPrice,
            @RequestParam String description,
            @RequestParam(required = false) Long referenceId,
            @RequestParam(required = false) String referenceType,
            @RequestParam(required = false) String referenceNumber) {
        try {
            com.cityfashionpos.model.TransactionType transactionType = com.cityfashionpos.model.TransactionType
                    .valueOf(type.toUpperCase());

            ProductTransactionDTO transaction = productTransactionService.createFromStockAdjustment(
                    productId, transactionType, quantity, unitPrice, description,
                    referenceId, referenceType, referenceNumber);

            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Health check endpoint
     * GET /api/product-transactions/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Product Transaction Service is running");
    }
}
