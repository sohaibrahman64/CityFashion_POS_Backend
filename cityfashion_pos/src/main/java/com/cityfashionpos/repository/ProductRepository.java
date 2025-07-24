package com.cityfashionpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	@Query(value = "SELECT MAX(id) FROM products", nativeQuery = true)
	Long findMaxProductId();

	boolean existsByBarcode(String barcode);
	
	boolean existsByBarcodeAndIdNot(String barcode, Long id);

	@Query(value = "SELECT * FROM products", nativeQuery = true)
	List<ProductEntity> findAllWithCategory();
	
	Optional<ProductEntity> findByBarcode(String barcode);
	
	@Query("SELECT p FROM ProductEntity p WHERE p.name = :name AND p.size = :size AND p.category.id = :categoryId")
    Optional<ProductEntity> findByNameAndSizeAndCategoryId(@Param("name") String name, @Param("size") String size, @Param("categoryId") Long categoryId);
	
	//Optional<ProductEntity> findByProduct(ProductEntity productEntity);
}
