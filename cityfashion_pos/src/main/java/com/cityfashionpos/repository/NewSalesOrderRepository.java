package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cityfashionpos.entity.NewSalesOrderEntity;

public interface NewSalesOrderRepository extends JpaRepository<NewSalesOrderEntity, Long> {
    @Query("SELECT MAX(so.id) FROM NewSalesOrderEntity so")
    Long findMaxSalesOrderId();

}
