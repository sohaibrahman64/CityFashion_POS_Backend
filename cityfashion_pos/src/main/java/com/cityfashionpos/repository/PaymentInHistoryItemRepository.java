package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PaymentInHistoryItemEntity;

@Repository
public interface PaymentInHistoryItemRepository extends JpaRepository<PaymentInHistoryItemEntity, Long> {

}
