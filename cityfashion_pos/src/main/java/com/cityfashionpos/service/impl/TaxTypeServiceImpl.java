package com.cityfashionpos.service.impl;

import com.cityfashionpos.entity.TaxTypeEntity;
import com.cityfashionpos.repository.TaxTypeRepository;
import com.cityfashionpos.service.TaxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaxTypeServiceImpl implements TaxTypeService {

    @Autowired
    private TaxTypeRepository taxTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaxTypeEntity> getAllTaxTypes() {
        return taxTypeRepository.findAllByOrderById();
    }

    @Override
    @Transactional(readOnly = true)
    public TaxTypeEntity getTaxTypeById(Long id) {
        Optional<TaxTypeEntity> taxType = taxTypeRepository.findById(id);
        return taxType.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public TaxTypeEntity getTaxTypeByCode(String taxTypeCode) {
        return taxTypeRepository.findByTaxTypeCode(taxTypeCode);
    }

    @Override
    public TaxTypeEntity createTaxType(TaxTypeEntity taxType) {
        return taxTypeRepository.save(taxType);
    }

    @Override
    public TaxTypeEntity updateTaxType(TaxTypeEntity taxType) {
        if (taxType.getId() == null) {
            throw new IllegalArgumentException("Tax type ID cannot be null for update");
        }

        // Check if the tax type exists
        if (!taxTypeRepository.existsById(taxType.getId())) {
            throw new IllegalArgumentException("Tax type with ID " + taxType.getId() + " not found");
        }

        return taxTypeRepository.save(taxType);
    }

    @Override
    public void deleteTaxType(Long id) {
        if (!taxTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Tax type with ID " + id + " not found");
        }
        taxTypeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTaxTypeCode(String taxTypeCode) {
        return taxTypeRepository.existsByTaxTypeCode(taxTypeCode);
    }
}
