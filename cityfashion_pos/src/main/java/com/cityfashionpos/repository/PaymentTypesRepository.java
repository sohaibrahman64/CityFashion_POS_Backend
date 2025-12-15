package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PaymentTypesEntity;

@Repository
public interface PaymentTypesRepository extends JpaRepository<PaymentTypesEntity, Long> {

}
