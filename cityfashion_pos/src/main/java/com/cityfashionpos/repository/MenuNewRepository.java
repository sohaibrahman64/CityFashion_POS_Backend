package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.MenuNewEntity;

@Repository
public interface MenuNewRepository extends JpaRepository<MenuNewEntity, Long> {

    /**
     * Find all active menus (where is_active = 1)
     */
    @Query("SELECT m FROM MenuNewEntity m WHERE m.isActive = 1 ORDER BY m.id")
    List<MenuNewEntity> findAllActiveMenus();
}
