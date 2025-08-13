package com.cityfashionpos.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.StockAdjustmentRequest;
import com.cityfashionpos.response.StockAdjustmentResponse;
import com.cityfashionpos.service.StockAdjustmentService;
import com.cityfashionpos.service.impl.StockAdjustmentServiceImpl;


@RestController
@RequestMapping("/api/stock-adjustments")
@CrossOrigin(origins = "*")
public class StockAdjustmentController {
    
    @Autowired
    private StockAdjustmentService stockAdjustmentService;
    
    /**
     * Create a new stock adjustment
     * POST /api/stock-adjustments
     */
    @PostMapping
    public ResponseEntity<StockAdjustmentResponse> createStockAdjustment(
            @RequestBody StockAdjustmentRequest request) {
        try {
            StockAdjustmentResponse response = stockAdjustmentService.createStockAdjustment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get all adjustments for a specific product
     * GET /api/stock-adjustments/product/{productId}
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockAdjustmentResponse>> getAdjustmentsByProduct(
            @PathVariable Long productId) {
        try {
            List<StockAdjustmentResponse> adjustments = stockAdjustmentService.getAdjustmentsByProduct(productId);
            return ResponseEntity.ok(adjustments);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Get adjustments by date range
     * GET /api/stock-adjustments/date-range?startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<StockAdjustmentResponse>> getAdjustmentsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            List<StockAdjustmentResponse> adjustments = stockAdjustmentService.getAdjustmentsByDateRange(startDate, endDate);
            return ResponseEntity.ok(adjustments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get adjustments by type
     * GET /api/stock-adjustments/type/{adjustmentType}
     */
    @GetMapping("/type/{adjustmentType}")
    public ResponseEntity<List<StockAdjustmentResponse>> getAdjustmentsByType(
            @PathVariable String adjustmentType) {
        try {
            List<StockAdjustmentResponse> adjustments = stockAdjustmentService.getAdjustmentsByType(adjustmentType);
            return ResponseEntity.ok(adjustments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Get adjustment by ID
     * GET /api/stock-adjustments/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<StockAdjustmentResponse> getAdjustmentById(@PathVariable Long id) {
        try {
            StockAdjustmentResponse adjustment = stockAdjustmentService.getAdjustmentById(id);
            return ResponseEntity.ok(adjustment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Delete adjustment
     * DELETE /api/stock-adjustments/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdjustment(@PathVariable Long id) {
        try {
            stockAdjustmentService.deleteAdjustment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Get adjustment summary for a product
     * GET /api/stock-adjustments/summary/{productId}?startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/summary/{productId}")
    public ResponseEntity<StockAdjustmentServiceImpl.StockAdjustmentSummary> getAdjustmentSummary(
            @PathVariable Long productId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            StockAdjustmentServiceImpl.StockAdjustmentSummary summary = 
                stockAdjustmentService.getAdjustmentSummary(productId, startDate, endDate);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Health check endpoint
     * GET /api/stock-adjustments/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Stock Adjustment Service is running");
    }
}