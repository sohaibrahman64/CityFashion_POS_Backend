package com.cityfashionpos.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.entity.BarcodeRequest;
import com.cityfashionpos.response.BarcodeResponse;
import com.cityfashionpos.service.BarcodeService;

@RestController
@RequestMapping("/api/barcode")
@CrossOrigin(origins = "*")
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping("/generateBarcode")
    public Map<String, String> generateBarcode() {
        String generated = barcodeService.generateNextBarcode();
        return Map.of("barcode", generated);
    }
    
//    @Autowired
//    private BarcodeService barcodeService;
    
    /**
     * Save multiple barcodes
     * POST /api/barcodes/save
     */
    @PostMapping("/saveBarcodes")
    public ResponseEntity<BarcodeResponse> saveBarcodes(@RequestBody BarcodeRequest request) {
        try {
            if (request.getBarcodes() == null || request.getBarcodes().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new BarcodeResponse(false, "Barcode data is required"));
            }
            
            BarcodeResponse response = barcodeService.saveBarcodes(request);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BarcodeResponse(false, "Internal server error: " + e.getMessage()));
        }
    }
    
    /**
     * Get all barcodes
     * GET /api/barcodes
     */
    @GetMapping("/getAllBarcodes")
    public ResponseEntity<BarcodeResponse> getAllBarcodes() {
        try {
            BarcodeResponse response = barcodeService.getAllBarcodes();
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BarcodeResponse(false, "Internal server error: " + e.getMessage()));
        }
    }
    
    /**
     * Get barcode by ID
     * GET /api/barcodes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BarcodeResponse> getBarcodeById(@PathVariable Long id) {
        try {
            BarcodeResponse response = barcodeService.getBarcodeById(id);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BarcodeResponse(false, "Internal server error: " + e.getMessage()));
        }
    }
    
    /**
     * Get barcode by product code
     * GET /api/barcodes/product/{productCode}
     */
    @GetMapping("/product/{productCode}")
    public ResponseEntity<BarcodeResponse> getBarcodeByProductCode(@PathVariable String productCode) {
        try {
            BarcodeResponse response = barcodeService.getBarcodeByProductCode(productCode);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BarcodeResponse(false, "Internal server error: " + e.getMessage()));
        }
    }
    
    /**
     * Update barcode
     * PUT /api/barcodes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<BarcodeResponse> updateBarcode(
            @PathVariable Long id, 
            @RequestBody BarcodeRequest.BarcodeData barcodeData) {
        try {
            BarcodeResponse response = barcodeService.updateBarcode(id, barcodeData);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BarcodeResponse(false, "Internal server error: " + e.getMessage()));
        }
    }
    
    /**
     * Delete barcode by ID
     * DELETE /api/barcodes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BarcodeResponse> deleteBarcodeById(@PathVariable Long id) {
        try {
            BarcodeResponse response = barcodeService.deleteBarcodeById(id);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BarcodeResponse(false, "Internal server error: " + e.getMessage()));
        }
    }
    
    /**
     * Health check endpoint
     * GET /api/barcodes/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Barcode API is running");
    }
}