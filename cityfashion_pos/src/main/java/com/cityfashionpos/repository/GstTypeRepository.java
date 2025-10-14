package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.GstTypeEntity;

@Repository
public interface GstTypeRepository extends JpaRepository<GstTypeEntity, Long> {

    /**
     * Find all active GST types (where is_active = 1)
     */
    @Query("SELECT g FROM GstTypeEntity g WHERE g.isActive = 1 ORDER BY g.id")
    List<GstTypeEntity> findAllActiveGstTypes();

    /**
     * Find all GST types (including inactive)
     */
    @Query("SELECT g FROM GstTypeEntity g ORDER BY g.id")
    List<GstTypeEntity> findAllGstTypes();
}
