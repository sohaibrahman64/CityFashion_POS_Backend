package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.NewDeliveryChallanItemEntity;

@Repository
public interface NewDeliveryChallanItemRepository extends JpaRepository<NewDeliveryChallanItemEntity, Long> {

}
