package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.LinkPaymentInTxnEntity;

@Repository
public interface LinkPaymentInTxnRepository extends JpaRepository<LinkPaymentInTxnEntity, Long> {
}
