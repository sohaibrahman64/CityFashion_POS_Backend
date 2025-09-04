package com.cityfashionpos.repository;

import com.cityfashionpos.entity.TaxRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRateEntity, Long> {

    /**
     * Find all active tax rates ordered by rate percentage
     */
    @Query("SELECT t FROM TaxRateEntity t WHERE t.active = true ORDER BY t.rate ASC")
    List<TaxRateEntity> findAllActiveOrderByRate();

    /**
     * Find all active tax rates by type ordered by rate percentage
     */
    @Query("SELECT t FROM TaxRateEntity t WHERE t.active = true AND t.type = :type ORDER BY t.rate ASC")
    List<TaxRateEntity> findActiveByTypeOrderByRate(String type);

    /**
     * Check if a tax rate exists and is active by ID
     */
    @Query("SELECT COUNT(t) > 0 FROM TaxRateEntity t WHERE t.id = :id AND t.active = true")
    boolean existsByIdAndActive(Long id);

    /**
     * Find all distinct labels from active tax rates
     */
    @Query("SELECT DISTINCT t.label FROM TaxRateEntity t WHERE t.active = true ORDER BY t.label ASC")
    List<String> findDistinctLabelsFromActiveTaxRates();
}
