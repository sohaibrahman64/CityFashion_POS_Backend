package com.cityfashionpos.repository;

import com.cityfashionpos.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    boolean existsByCode(String code);

    Optional<ItemEntity> findByCode(String code);

    boolean existsByNameIgnoreCase(String name);

    Optional<ItemEntity> findById(Long id);
}