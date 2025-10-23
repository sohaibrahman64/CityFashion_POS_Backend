package com.cityfashionpos.repository;

import com.cityfashionpos.entity.TaxTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxTypeRepository extends JpaRepository<TaxTypeEntity, Long> {

    /**
     * Find all tax types ordered by id
     */
    List<TaxTypeEntity> findAllByOrderById();

    /**
     * Find tax type by tax type code
     */
    TaxTypeEntity findByTaxTypeCode(String taxTypeCode);

    /**
     * Check if tax type exists by tax type code
     */
    boolean existsByTaxTypeCode(String taxTypeCode);
}
