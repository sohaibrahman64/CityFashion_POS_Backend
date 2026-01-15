package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cityfashionpos.entity.NewSalesOrderItemEntity;

public interface NewSalesOrderItemRepository extends JpaRepository<NewSalesOrderItemEntity, Long> {

}
