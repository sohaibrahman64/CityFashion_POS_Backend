package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PaymentInHistoryEntity;

@Repository
public interface PaymentInHistoryRepository extends JpaRepository<PaymentInHistoryEntity, Long> {

}
