package com.cityfashionpos.service;

import com.cityfashionpos.entity.UnitsEntity;

import java.util.List;

public interface UnitsService {

    /**
     * Get all units
     */
    List<UnitsEntity> getAllUnits();

    /**
     * Get unit by ID
     */
    UnitsEntity getUnitById(Long id);

    /**
     * Get unit by unit name
     */
    UnitsEntity getUnitByName(String unitName);

    /**
     * Get unit by unit abbreviation
     */
    UnitsEntity getUnitByAbbr(String unitAbbr);

    /**
     * Create a new unit
     */
    UnitsEntity createUnit(UnitsEntity unit);

    /**
     * Update an existing unit
     */
    UnitsEntity updateUnit(UnitsEntity unit);

    /**
     * Delete a unit
     */
    void deleteUnit(Long id);

    /**
     * Check if unit exists by name
     */
    boolean existsByUnitName(String unitName);

    /**
     * Check if unit exists by abbreviation
     */
    boolean existsByUnitAbbr(String unitAbbr);
}
