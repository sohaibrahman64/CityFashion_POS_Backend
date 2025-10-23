package com.cityfashionpos.repository;

import com.cityfashionpos.entity.DiscountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountTypeRepository extends JpaRepository<DiscountTypeEntity, Long> {

    /**
     * Find all discount types ordered by id
     */
    List<DiscountTypeEntity> findAllByOrderById();

    /**
     * Find discount type by discount type code
     */
    DiscountTypeEntity findByDiscountTypeCode(String discountTypeCode);

    /**
     * Check if discount type exists by discount type code
     */
    boolean existsByDiscountTypeCode(String discountTypeCode);
}
