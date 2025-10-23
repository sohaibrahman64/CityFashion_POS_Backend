package com.cityfashionpos.repository;

import com.cityfashionpos.entity.UnitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitsRepository extends JpaRepository<UnitsEntity, Long> {

    /**
     * Find all units ordered by id
     */
    List<UnitsEntity> findAllByOrderById();

    /**
     * Find unit by unit name
     */
    UnitsEntity findByUnitName(String unitName);

    /**
     * Find unit by unit abbreviation
     */
    UnitsEntity findByUnitAbbr(String unitAbbr);

    /**
     * Check if unit exists by unit name
     */
    boolean existsByUnitName(String unitName);

    /**
     * Check if unit exists by unit abbreviation
     */
    boolean existsByUnitAbbr(String unitAbbr);
}
