package com.cityfashionpos.service;

import com.cityfashionpos.entity.TaxTypeEntity;

import java.util.List;

public interface TaxTypeService {

    /**
     * Get all tax types
     */
    List<TaxTypeEntity> getAllTaxTypes();

    /**
     * Get tax type by ID
     */
    TaxTypeEntity getTaxTypeById(Long id);

    /**
     * Get tax type by tax type code
     */
    TaxTypeEntity getTaxTypeByCode(String taxTypeCode);

    /**
     * Create a new tax type
     */
    TaxTypeEntity createTaxType(TaxTypeEntity taxType);

    /**
     * Update an existing tax type
     */
    TaxTypeEntity updateTaxType(TaxTypeEntity taxType);

    /**
     * Delete a tax type
     */
    void deleteTaxType(Long id);

    /**
     * Check if tax type exists by code
     */
    boolean existsByTaxTypeCode(String taxTypeCode);
}
