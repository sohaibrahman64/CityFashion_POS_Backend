package com.cityfashionpos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.entity.SupplierEntity;
import com.cityfashionpos.repository.SupplierRepository;
import com.cityfashionpos.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierEntity saveSupplier(SupplierEntity supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<SupplierEntity> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<SupplierEntity> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
