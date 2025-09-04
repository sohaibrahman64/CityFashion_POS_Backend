package com.cityfashionpos.controller;

import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.service.TaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax-rates")
@CrossOrigin(origins = "*")
public class TaxRateController {

    @Autowired
    private TaxRateService taxRateService;

    /**
     * Get all active tax rates sorted by percentage
     * GET /api/tax-rates
     */
    @GetMapping("/getAllActiveTaxRates")
    public ResponseEntity<List<TaxRateEntity>> getAllActiveTaxRates() {
        try {
            List<TaxRateEntity> taxRates = taxRateService.getAllActiveTaxRates();
            return ResponseEntity.ok(taxRates);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get active tax rates by type
     * GET /api/tax-rates/type/{type}
     */
    @GetMapping("/getActiveTaxRatesByType/{type}")
    public ResponseEntity<List<TaxRateEntity>> getActiveTaxRatesByType(@PathVariable String type) {
        try {
            List<TaxRateEntity> taxRates = taxRateService.getActiveTaxRatesByType(type);
            return ResponseEntity.ok(taxRates);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get tax rate by ID
     * GET /api/tax-rates/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaxRateEntity> getTaxRateById(@PathVariable Long id) {
        try {
            TaxRateEntity taxRate = taxRateService.getTaxRateById(id);
            if (taxRate != null) {
                return ResponseEntity.ok(taxRate);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Create a new tax rate
     * POST /api/tax-rates
     */
    @PostMapping("/createTaxRate")
    public ResponseEntity<TaxRateEntity> createTaxRate(@RequestBody TaxRateEntity taxRate) {
        try {
            TaxRateEntity createdTaxRate = taxRateService.createTaxRate(taxRate);
            return ResponseEntity.ok(createdTaxRate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an existing tax rate
     * PUT /api/tax-rates/{id}
     */
    @PutMapping("/updateTaxRate/{id}")
    public ResponseEntity<TaxRateEntity> updateTaxRate(@PathVariable Long id, @RequestBody TaxRateEntity taxRate) {
        try {
            taxRate.setId(id);
            TaxRateEntity updatedTaxRate = taxRateService.updateTaxRate(taxRate);
            return ResponseEntity.ok(updatedTaxRate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Deactivate a tax rate
     * DELETE /api/tax-rates/{id}
     */
    @DeleteMapping("/deactivateTaxRate/{id}")
    public ResponseEntity<Void> deactivateTaxRate(@PathVariable Long id) {
        try {
            taxRateService.deactivateTaxRate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get all distinct labels from active tax rates
     * GET /api/tax-rates/labels
     */
    @GetMapping("/getAllActiveTaxRateLabels")
    public ResponseEntity<List<String>> getAllActiveTaxRateLabels() {
        try {
            List<String> labels = taxRateService.getAllActiveTaxRateLabels();
            return ResponseEntity.ok(labels);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
