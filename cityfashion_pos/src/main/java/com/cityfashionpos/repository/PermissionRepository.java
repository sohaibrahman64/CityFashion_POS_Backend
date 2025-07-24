package com.cityfashionpos.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.MenuEntity;
import com.cityfashionpos.entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>{
	//@Query("SELECT p.menu FROM PermissionEntity p WHERE p.role.id = :roleid AND p.canView = true")
	@Query("SELECT m FROM PermissionEntity p JOIN p.menu m WHERE p.role.id = :roleid AND p.canView = true ORDER BY \r\n" + 
			  "CASE \r\n" +
			    "WHEN m.id IN (9, 10, 11) THEN 1 \r\n" +
			    "ELSE 0 \r\n" +
			  "END, \r\n" +
			  "CASE \r\n" +
			    "WHEN m.id IN (9, 10, 11) THEN \r\n" +
			      "CASE m.id \r\n" + 
			        "WHEN 9 THEN 1 \r\n" +
			        "WHEN 10 THEN 2 \r\n" +
			        "WHEN 11 THEN 3 \r\n" +
			      "END \r\n" +
			    "ELSE m.id \r\n" +
			  "END")
    List<MenuEntity> findMenusByRoleId(@Param("roleid") Long roleId);
}
