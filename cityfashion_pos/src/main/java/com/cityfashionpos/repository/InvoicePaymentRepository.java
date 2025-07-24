package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cityfashionpos.entity.InvoicePaymentEntity;

public interface InvoicePaymentRepository extends JpaRepository<InvoicePaymentEntity, Long>{
	List<InvoicePaymentEntity> findByInvoiceId(Long invoiceId);
}
