package com.cityfashionpos.controller;

import com.cityfashionpos.entity.DiscountTypeEntity;
import com.cityfashionpos.service.DiscountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount-types")
@CrossOrigin(origins = "*")
public class DiscountTypeController {

    @Autowired
    private DiscountTypeService discountTypeService;

    /**
     * Get all discount types
     * GET /api/discount-types/all
     */
    @GetMapping("/all")
    public ResponseEntity<List<DiscountTypeEntity>> getAllDiscountTypes() {
        try {
            List<DiscountTypeEntity> discountTypes = discountTypeService.getAllDiscountTypes();
            return ResponseEntity.ok(discountTypes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get discount type by ID
     * GET /api/discount-types/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiscountTypeEntity> getDiscountTypeById(@PathVariable Long id) {
        try {
            DiscountTypeEntity discountType = discountTypeService.getDiscountTypeById(id);
            if (discountType != null) {
                return ResponseEntity.ok(discountType);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get discount type by code
     * GET /api/discount-types/code/{code}
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<DiscountTypeEntity> getDiscountTypeByCode(@PathVariable String code) {
        try {
            DiscountTypeEntity discountType = discountTypeService.getDiscountTypeByCode(code);
            if (discountType != null) {
                return ResponseEntity.ok(discountType);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Create a new discount type
     * POST /api/discount-types
     */
    @PostMapping
    public ResponseEntity<DiscountTypeEntity> createDiscountType(@RequestBody DiscountTypeEntity discountType) {
        try {
            DiscountTypeEntity createdDiscountType = discountTypeService.createDiscountType(discountType);
            return ResponseEntity.ok(createdDiscountType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an existing discount type
     * PUT /api/discount-types/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<DiscountTypeEntity> updateDiscountType(@PathVariable Long id,
            @RequestBody DiscountTypeEntity discountType) {
        try {
            discountType.setId(id);
            DiscountTypeEntity updatedDiscountType = discountTypeService.updateDiscountType(discountType);
            return ResponseEntity.ok(updatedDiscountType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a discount type
     * DELETE /api/discount-types/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountType(@PathVariable Long id) {
        try {
            discountTypeService.deleteDiscountType(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
