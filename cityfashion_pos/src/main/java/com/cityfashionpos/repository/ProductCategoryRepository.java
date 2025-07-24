package com.cityfashionpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.ProductCategoryEntity;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
	@Query(value = "SELECT * FROM product_categories WHERE is_active = true ORDER BY Name ASC", nativeQuery=true)
	List<ProductCategoryEntity> findByActiveTrue();
	
	Optional<ProductCategoryEntity> findByName(String categoryEntity);
}
