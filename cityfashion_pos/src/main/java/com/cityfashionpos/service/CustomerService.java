package com.cityfashionpos.service;

import java.util.List;
import java.util.Optional;

import com.cityfashionpos.entity.CustomerEntity;

public interface CustomerService {
    CustomerEntity saveCustomer(CustomerEntity customer);
    List<CustomerEntity> getAllCustomers();
    Optional<CustomerEntity> getCustomerById(Long id);
    List<CustomerEntity> searchByName(String name);
    CustomerEntity updateCustomer(Long id, CustomerEntity updatedCustomer);
    void deleteCustomer(Long id);
}
