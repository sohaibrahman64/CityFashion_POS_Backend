package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.StateEntity;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {

    /**
     * Find all active states (where is_active = 1)
     */
    @Query("SELECT s FROM StateEntity s WHERE s.isActive = 1 ORDER BY s.state")
    List<StateEntity> findAllActiveStates();

    /**
     * Find all states (including inactive)
     */
    @Query("SELECT s FROM StateEntity s ORDER BY s.state")
    List<StateEntity> findAllStates();
}
