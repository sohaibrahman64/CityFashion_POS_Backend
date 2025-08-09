package com.cityfashionpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.entity.ProductEntityNew;

@EnableJpaRepositories
public interface ProductRepositoryNew extends JpaRepository<ProductEntityNew, Long>{
    // Find by product code
    Optional<ProductEntityNew> findByCode(String code);
    
    // Find by product name (case-insensitive)
    @Query("SELECT p FROM ProductEntityNew p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<ProductEntityNew> findByNameContainingIgnoreCase(@Param("name") String name);
    
    // Find by category
    List<ProductEntityNew> findByCategory(String category);
    
    // Find by HSN code
    List<ProductEntityNew> findByHsn(String hsn);
    
    // Check if product code exists
    boolean existsByCode(String code);
    
    // Find products with low stock (below minimum stock)
    @Query("SELECT p FROM ProductEntityNew p WHERE p.openingQuantity <= p.minStock")
    List<ProductEntityNew> findProductsWithLowStock();
    
    // Find products by price range
    @Query("SELECT p FROM ProductEntityNew p WHERE p.salePrice BETWEEN :minPrice AND :maxPrice")
    List<ProductEntityNew> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
    
    // Find products created in date range
    @Query("SELECT p FROM ProductEntityNew p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    List<ProductEntityNew> findByCreatedDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    
    // Count products by category
    @Query("SELECT p.category, COUNT(p) FROM ProductEntityNew p GROUP BY p.category")
    List<Object[]> countByCategory();
}
