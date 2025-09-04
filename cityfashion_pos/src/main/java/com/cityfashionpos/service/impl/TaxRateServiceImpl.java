package com.cityfashionpos.service.impl;

import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.repository.TaxRateRepository;
import com.cityfashionpos.service.TaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaxRateServiceImpl implements TaxRateService {

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaxRateEntity> getAllActiveTaxRates() {
        return taxRateRepository.findAllActiveOrderByRate();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaxRateEntity> getActiveTaxRatesByType(String type) {
        return taxRateRepository.findActiveByTypeOrderByRate(type);
    }

    @Override
    @Transactional(readOnly = true)
    public TaxRateEntity getTaxRateById(Long id) {
        Optional<TaxRateEntity> taxRate = taxRateRepository.findById(id);
        return taxRate.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaxRateActive(Long id) {
        return taxRateRepository.existsByIdAndActive(id);
    }

    @Override
    public TaxRateEntity createTaxRate(TaxRateEntity taxRate) {
        // Ensure the tax rate is active by default
        if (taxRate.getActive() == null) {
            taxRate.setActive(true);
        }
        return taxRateRepository.save(taxRate);
    }

    @Override
    public TaxRateEntity updateTaxRate(TaxRateEntity taxRate) {
        if (taxRate.getId() == null) {
            throw new IllegalArgumentException("Tax rate ID cannot be null for update");
        }

        // Check if the tax rate exists
        if (!taxRateRepository.existsById(taxRate.getId())) {
            throw new IllegalArgumentException("Tax rate with ID " + taxRate.getId() + " not found");
        }

        return taxRateRepository.save(taxRate);
    }

    @Override
    public void deactivateTaxRate(Long id) {
        Optional<TaxRateEntity> taxRateOpt = taxRateRepository.findById(id);
        if (taxRateOpt.isPresent()) {
            TaxRateEntity taxRate = taxRateOpt.get();
            taxRate.setActive(false);
            taxRateRepository.save(taxRate);
        } else {
            throw new IllegalArgumentException("Tax rate with ID " + id + " not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllActiveTaxRateLabels() {
        return taxRateRepository.findDistinctLabelsFromActiveTaxRates();
    }
}
