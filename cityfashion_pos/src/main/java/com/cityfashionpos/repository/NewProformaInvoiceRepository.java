package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cityfashionpos.entity.NewProformaInvoiceEntity;

public interface NewProformaInvoiceRepository extends JpaRepository<NewProformaInvoiceEntity, Long> {
    @Query("SELECT MAX(pi.id) FROM NewProformaInvoiceEntity pi")
    Long findMaxProformaInvoiceId();
}
