package com.cityfashionpos.service;

import com.cityfashionpos.entity.DiscountTypeEntity;

import java.util.List;

public interface DiscountTypeService {

    /**
     * Get all discount types
     */
    List<DiscountTypeEntity> getAllDiscountTypes();

    /**
     * Get discount type by ID
     */
    DiscountTypeEntity getDiscountTypeById(Long id);

    /**
     * Get discount type by discount type code
     */
    DiscountTypeEntity getDiscountTypeByCode(String discountTypeCode);

    /**
     * Create a new discount type
     */
    DiscountTypeEntity createDiscountType(DiscountTypeEntity discountType);

    /**
     * Update an existing discount type
     */
    DiscountTypeEntity updateDiscountType(DiscountTypeEntity discountType);

    /**
     * Delete a discount type
     */
    void deleteDiscountType(Long id);

    /**
     * Check if discount type exists by code
     */
    boolean existsByDiscountTypeCode(String discountTypeCode);
}
