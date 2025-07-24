package com.cityfashionpos.service;

import java.util.List;
import java.util.Optional;

import com.cityfashionpos.entity.SupplierEntity;

public interface SupplierService {
    SupplierEntity saveSupplier(SupplierEntity supplier);
    List<SupplierEntity> getAllSuppliers();
    Optional<SupplierEntity> getSupplierById(Long id);
    void deleteSupplier(Long id);
}
