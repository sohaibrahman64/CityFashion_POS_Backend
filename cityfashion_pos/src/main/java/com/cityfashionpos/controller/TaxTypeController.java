package com.cityfashionpos.controller;

import com.cityfashionpos.entity.TaxTypeEntity;
import com.cityfashionpos.service.TaxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax-types")
@CrossOrigin(origins = "*")
public class TaxTypeController {

    @Autowired
    private TaxTypeService taxTypeService;

    /**
     * Get all tax types
     * GET /api/tax-types
     */
    @GetMapping("/all")
    public ResponseEntity<List<TaxTypeEntity>> getAllTaxTypes() {
        try {
            List<TaxTypeEntity> taxTypes = taxTypeService.getAllTaxTypes();
            return ResponseEntity.ok(taxTypes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get tax type by ID
     * GET /api/tax-types/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaxTypeEntity> getTaxTypeById(@PathVariable Long id) {
        try {
            TaxTypeEntity taxType = taxTypeService.getTaxTypeById(id);
            if (taxType != null) {
                return ResponseEntity.ok(taxType);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get tax type by code
     * GET /api/tax-types/code/{code}
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<TaxTypeEntity> getTaxTypeByCode(@PathVariable String code) {
        try {
            TaxTypeEntity taxType = taxTypeService.getTaxTypeByCode(code);
            if (taxType != null) {
                return ResponseEntity.ok(taxType);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Create a new tax type
     * POST /api/tax-types
     */
    @PostMapping
    public ResponseEntity<TaxTypeEntity> createTaxType(@RequestBody TaxTypeEntity taxType) {
        try {
            TaxTypeEntity createdTaxType = taxTypeService.createTaxType(taxType);
            return ResponseEntity.ok(createdTaxType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an existing tax type
     * PUT /api/tax-types/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaxTypeEntity> updateTaxType(@PathVariable Long id, @RequestBody TaxTypeEntity taxType) {
        try {
            taxType.setId(id);
            TaxTypeEntity updatedTaxType = taxTypeService.updateTaxType(taxType);
            return ResponseEntity.ok(updatedTaxType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a tax type
     * DELETE /api/tax-types/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxType(@PathVariable Long id) {
        try {
            taxTypeService.deleteTaxType(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
