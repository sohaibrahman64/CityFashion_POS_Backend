package com.cityfashionpos.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
	//@Query("SELECT * FROM menu m JOIN permissions p ON m.id = p.menu_id WHERE p.role_id = :roleid AND m.is_active = true")
    //List<MenuEntity> findMenusByRoleId(@Param("roleid") Long roleId);
}
