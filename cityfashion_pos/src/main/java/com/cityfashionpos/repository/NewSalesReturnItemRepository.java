package com.cityfashionpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cityfashionpos.entity.NewSalesReturnItemEntity;
import com.cityfashionpos.entity.TaxRateEntity;

public interface NewSalesReturnItemRepository extends JpaRepository<NewSalesReturnItemEntity, Long> {
    List<NewSalesReturnItemEntity> findBySalesReturnId(Long salesReturnId);

    @Query("SELECT SUM(item.total) FROM NewSalesReturnItemEntity item WHERE item.salesReturnId = :salesReturnId")
    Double calculateTotalAmount(@Param("salesReturnId") Long salesReturnId);

    @Query("SELECT SUM(item.discountAmount) FROM NewSalesReturnItemEntity item WHERE item.salesReturnId = :salesReturnId")
    Double calculateTotalDiscountAmount(@Param("salesReturnId") Long salesReturnId);

    @Query("SELECT SUM(item.total) - SUM(item.discountAmount) FROM NewSalesReturnItemEntity item WHERE item.salesReturnId = :salesReturnId")
    Double calculateTotalAmountAfterDiscount(@Param("salesReturnId") Long salesReturnId);

    @Query("SELECT SUM(item.total) - SUM(item.discountAmount) - SUM(item.taxPercent) FROM NewSalesReturnItemEntity item WHERE item.salesReturnId = :salesReturnId")
    Double calculateTotalAmountAfterTax(@Param("salesReturnId") Long salesReturnId);

    Optional<TaxRateEntity> findByTaxRateId(Long id);
}
