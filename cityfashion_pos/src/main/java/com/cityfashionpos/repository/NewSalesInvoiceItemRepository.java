package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.entity.NewSalesInvoiceItemEntity;

public interface NewSalesInvoiceItemRepository extends JpaRepository<NewSalesInvoiceItemEntity, Long> {
    List<NewSalesInvoiceItemEntity> findByInvoiceId(Long invoiceId);

    @Query("SELECT SUM(item.total) FROM NewSalesInvoiceItemEntity item WHERE item.invoiceId = :invoiceId")
    Double calculateTotalAmount(@Param("invoiceId") Long invoiceId);

    @Query("SELECT SUM(item.discountAmount) FROM NewSalesInvoiceItemEntity item WHERE item.invoiceId = :invoiceId")
    Double calculateTotalDiscountAmount(@Param("invoiceId") Long invoiceId);

    @Query("SELECT SUM(item.total) - SUM(item.discountAmount) FROM NewSalesInvoiceItemEntity item WHERE item.invoiceId = :invoiceId")
    Double calculateTotalAmountAfterDiscount(@Param("invoiceId") Long invoiceId);

    @Query("SELECT SUM(item.total) - SUM(item.discountAmount) - SUM(item.taxPercent) FROM NewSalesInvoiceItemEntity item WHERE item.invoiceId = :invoiceId")
    Double calculateTotalAmountAfterTax(@Param("invoiceId") Long invoiceId);
}
