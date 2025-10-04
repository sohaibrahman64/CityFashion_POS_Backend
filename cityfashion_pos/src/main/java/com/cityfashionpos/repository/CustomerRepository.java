package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    List<CustomerEntity> findByNameContainingIgnoreCase(String keyword);

    // Optional<CustomerEntity> findByCustomer(CustomerEntity customer);
}
