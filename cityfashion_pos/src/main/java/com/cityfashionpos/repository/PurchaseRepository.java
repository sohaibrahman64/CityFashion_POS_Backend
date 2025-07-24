package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PurchaseEntity;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
	@Query("SELECT p FROM PurchaseEntity p LEFT JOIN FETCH p.items i LEFT JOIN FETCH i.product s ORDER BY p.purchaseDate DESC")
	List<PurchaseEntity> findAllWithItems();
}
