package com.cityfashionpos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cityfashionpos.entity.InventoryMovementTypeEntity;

public interface InventoryMovementsTypeRepository extends JpaRepository<InventoryMovementTypeEntity, Long>{
    Optional<InventoryMovementTypeEntity> findByMovementType(String movementType);
}
