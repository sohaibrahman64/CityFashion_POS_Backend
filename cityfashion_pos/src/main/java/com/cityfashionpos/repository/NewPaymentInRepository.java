package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.NewPaymentInEntity;

@Repository
public interface NewPaymentInRepository extends JpaRepository<NewPaymentInEntity, Long> {
    @Query("SELECT MAX(payment_in.id) FROM NewPaymentInEntity payment_in")
    Long findMaxPaymentInId();

}
