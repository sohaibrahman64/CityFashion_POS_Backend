package com.cityfashionpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.NewDeliveryChallanEntity;

@Repository
public interface NewDeliveryChallanRepository extends JpaRepository<NewDeliveryChallanEntity, Long> {

    @Query("SELECT MAX(dc.id) FROM NewDeliveryChallanEntity dc")
    Long findMaxDeliveryChallanId();

}
