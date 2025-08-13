package com.cityfashionpos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.entity.StockAdjustmentsEntity;

public interface StockAdjustmentRepository extends JpaRepository<StockAdjustmentsEntity, Long> {
    /**
     * Find all adjustments for a specific product
     */
    List<StockAdjustmentsEntity> findByProductIdOrderByAdjustmentDateDesc(Long productId);
    
    /**
     * Find adjustments by product and date range
     */
    @Query("SELECT sa FROM StockAdjustmentsEntity sa WHERE sa.product.id = :productId " +
           "AND sa.adjustmentDate BETWEEN :startDate AND :endDate " +
           "ORDER BY sa.adjustmentDate DESC")
    List<StockAdjustmentsEntity> findByProductAndDateRange(
        @Param("productId") Long productId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    /**
     * Find adjustments by adjustment type
     */
    List<StockAdjustmentsEntity> findByAdjustmentTypeOrderByAdjustmentDateDesc(String adjustmentType);
    
    /**
     * Find adjustments by date range
     */
    @Query("SELECT sa FROM StockAdjustmentsEntity sa WHERE sa.adjustmentDate BETWEEN :startDate AND :endDate " +
           "ORDER BY sa.adjustmentDate DESC")
    List<StockAdjustmentsEntity> findByDateRange(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    /**
     * Find adjustments by created by user
     */
    List<StockAdjustmentsEntity> findByCreatedByOrderByCreatedAtDesc(String createdBy);
    
    /**
     * Get total adjustments value for a product in a date range
     */
    @Query("SELECT SUM(sa.totalValue) FROM StockAdjustmentsEntity sa WHERE sa.product.id = :productId " +
           "AND sa.adjustmentDate BETWEEN :startDate AND :endDate")
    Double getTotalAdjustmentValueForProduct(
        @Param("productId") Long productId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    /**
     * Get count of adjustments by type for a product
     */
    @Query("SELECT COUNT(sa) FROM StockAdjustmentsEntity sa WHERE sa.product.id = :productId " +
           "AND sa.adjustmentType = :adjustmentType")
    Long countByProductAndAdjustmentType(
        @Param("productId") Long productId,
        @Param("adjustmentType") String adjustmentType
    );
}
