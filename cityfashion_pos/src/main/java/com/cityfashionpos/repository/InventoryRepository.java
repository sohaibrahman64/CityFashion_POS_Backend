package com.cityfashionpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.dto.InventoryDTO;
import com.cityfashionpos.entity.InventoryEntity;
import com.cityfashionpos.entity.ProductEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
	@Query("SELECT new com.cityfashionpos.dto.InventoryDTO(i.id, p.name, p.barcode, p.size, i.quantity) "
			+ "FROM InventoryEntity i " + "JOIN i.product p ")
	List<InventoryDTO> getInventoryList();
	
	Optional<InventoryEntity> findByProductId(Long productId);
	
	@Query("SELECT i FROM InventoryEntity i JOIN i.product p WHERE p.barcode = :barcode")
	Optional<InventoryEntity> findByProductBarcode(@Param("barcode") String barcode);
	
	Optional<InventoryEntity> findByProduct(ProductEntity product);

}
