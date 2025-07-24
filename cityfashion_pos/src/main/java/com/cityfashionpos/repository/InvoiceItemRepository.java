package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cityfashionpos.entity.InvoiceItemEntity;
import com.cityfashionpos.entity.ProductEntity;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long> {
	List<InvoiceItemEntity> findByInvoiceId(Long invoiceId);
}
