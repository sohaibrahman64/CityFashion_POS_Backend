package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cityfashionpos.entity.InventoryMovementsEntity;

public interface InventoryMovementsRepository extends JpaRepository<InventoryMovementsEntity, Long>{

}
