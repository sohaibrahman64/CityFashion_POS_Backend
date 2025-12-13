package com.cityfashionpos.service.impl;

import com.cityfashionpos.entity.DiscountTypeEntity;
import com.cityfashionpos.repository.DiscountTypeRepository;
import com.cityfashionpos.service.DiscountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiscountTypeServiceImpl implements DiscountTypeService {

    @Autowired
    private DiscountTypeRepository discountTypeRepository;

    @Override
    @Transactional
    public List<DiscountTypeEntity> getAllDiscountTypes() {
        return discountTypeRepository.findAllByOrderById();
    }

    @Override
    @Transactional
    public DiscountTypeEntity getDiscountTypeById(Long id) {
        Optional<DiscountTypeEntity> discountType = discountTypeRepository.findById(id);
        return discountType.orElse(null);
    }

    @Override
    @Transactional
    public DiscountTypeEntity getDiscountTypeByCode(String discountTypeCode) {
        return discountTypeRepository.findByDiscountTypeCode(discountTypeCode);
    }

    @Override
    public DiscountTypeEntity createDiscountType(DiscountTypeEntity discountType) {
        return discountTypeRepository.save(discountType);
    }

    @Override
    public DiscountTypeEntity updateDiscountType(DiscountTypeEntity discountType) {
        if (discountType.getId() == null) {
            throw new IllegalArgumentException("Discount type ID cannot be null for update");
        }

        // Check if the discount type exists
        if (!discountTypeRepository.existsById(discountType.getId())) {
            throw new IllegalArgumentException("Discount type with ID " + discountType.getId() + " not found");
        }

        return discountTypeRepository.save(discountType);
    }

    @Override
    public void deleteDiscountType(Long id) {
        if (!discountTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Discount type with ID " + id + " not found");
        }
        discountTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean existsByDiscountTypeCode(String discountTypeCode) {
        return discountTypeRepository.existsByDiscountTypeCode(discountTypeCode);
    }
}
