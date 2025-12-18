package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PaymentTypesEntity;

@Repository
public interface PaymentTypesRepository extends JpaRepository<PaymentTypesEntity, Long> {

    @Query("SELECT pt FROM PaymentTypesEntity pt ORDER BY pt.id")
    List<PaymentTypesEntity> getAllPaymentTypes();

}
