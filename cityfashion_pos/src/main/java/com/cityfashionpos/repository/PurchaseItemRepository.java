package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PurchaseItemEntity;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItemEntity, Long> {

}
