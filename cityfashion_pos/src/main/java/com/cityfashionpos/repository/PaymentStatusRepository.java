package com.cityfashionpos.repository;

import com.cityfashionpos.entity.PaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, Long> {

    PaymentStatusEntity findByStatusName(String statusName);
}
