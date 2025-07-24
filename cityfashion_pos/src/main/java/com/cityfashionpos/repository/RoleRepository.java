package com.cityfashionpos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cityfashionpos.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByName(String name);
}
