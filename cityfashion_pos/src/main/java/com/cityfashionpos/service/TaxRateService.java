package com.cityfashionpos.service;

import com.cityfashionpos.entity.TaxRateEntity;

import java.util.List;

public interface TaxRateService {

    /**
     * Get all active tax rates ordered by rate percentage
     */
    List<TaxRateEntity> getAllActiveTaxRates();

    /**
     * Get all active tax rates by type ordered by rate percentage
     */
    List<TaxRateEntity> getActiveTaxRatesByType(String type);

    /**
     * Get tax rate by ID
     */
    TaxRateEntity getTaxRateById(Long id);

    /**
     * Check if tax rate exists and is active
     */
    boolean isTaxRateActive(Long id);

    /**
     * Create a new tax rate
     */
    TaxRateEntity createTaxRate(TaxRateEntity taxRate);

    /**
     * Update an existing tax rate
     */
    TaxRateEntity updateTaxRate(TaxRateEntity taxRate);

    /**
     * Deactivate a tax rate (soft delete)
     */
    void deactivateTaxRate(Long id);

    /**
     * Get all distinct labels from active tax rates
     */
    List<String> getAllActiveTaxRateLabels();
}
