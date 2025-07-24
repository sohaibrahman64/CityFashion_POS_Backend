package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PaymentModeEntity;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentModeEntity, Long> {
	
}

