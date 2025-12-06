package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.EstimateQuotationTransactionEntity;

@Repository
public interface EstimateQuotationTransactionRepository
                extends JpaRepository<EstimateQuotationTransactionEntity, Long> {

    /**
     * Get maximum transaction ID for generating transaction numbers
     */
    @Query("SELECT MAX(eqt.id) FROM EstimateQuotationTransactionEntity eqt")
    Long findMaxTransactionId();

}
