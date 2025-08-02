package com.cityfashionpos.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.entity.BarcodeEntity;

public interface BarcodeRepository extends JpaRepository<BarcodeEntity, Long> {
    /**
     * Find barcode by product code
     */
    Optional<BarcodeEntity> findByProductCode(String productCode);
    
    /**
     * Find barcodes by product name (case-insensitive)
     */
    List<BarcodeEntity> findByProductNameContainingIgnoreCase(String productName);
    
    /**
     * Find barcodes by printer type
     */
    List<BarcodeEntity> findByPrinterType(String printerType);
    
    /**
     * Find barcodes by size option
     */
    List<BarcodeEntity> findBySizeOption(String sizeOption);
    
    /**
     * Check if product code exists
     */
    boolean existsByProductCode(String productCode);
    
    /**
     * Find barcodes created between two dates
     */
    @Query("SELECT b FROM BarcodeEntity b WHERE b.createdAt BETWEEN :startDate AND :endDate")
    List<BarcodeEntity> findByCreatedAtBetween(@Param("startDate") java.time.LocalDateTime startDate, 
                                        @Param("endDate") java.time.LocalDateTime endDate);
    
    /**
     * Find barcodes with number of labels greater than specified value
     */
    List<BarcodeEntity> findByNumLabelsGreaterThan(Integer numLabels);
    
    /**
     * Count barcodes by product name
     */
    @Query("SELECT COUNT(b) FROM BarcodeEntity b WHERE b.productName = :productName")
    long countByProductName(@Param("productName") String productName);
}
