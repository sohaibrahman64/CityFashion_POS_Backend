package com.cityfashionpos.service.impl;

import com.cityfashionpos.entity.UnitsEntity;
import com.cityfashionpos.repository.UnitsRepository;
import com.cityfashionpos.service.UnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitsServiceImpl implements UnitsService {

    @Autowired
    private UnitsRepository unitsRepository;

    @Override
    @Transactional
    public List<UnitsEntity> getAllUnits() {
        return unitsRepository.findAllByOrderById();
    }

    @Override
    @Transactional
    public UnitsEntity getUnitById(Long id) {
        Optional<UnitsEntity> unit = unitsRepository.findById(id);
        return unit.orElse(null);
    }

    @Override
    @Transactional
    public UnitsEntity getUnitByName(String unitName) {
        return unitsRepository.findByUnitName(unitName);
    }

    @Override
    @Transactional
    public UnitsEntity getUnitByAbbr(String unitAbbr) {
        return unitsRepository.findByUnitAbbr(unitAbbr);
    }

    @Override
    public UnitsEntity createUnit(UnitsEntity unit) {
        return unitsRepository.save(unit);
    }

    @Override
    public UnitsEntity updateUnit(UnitsEntity unit) {
        if (unit.getId() == null) {
            throw new IllegalArgumentException("Unit ID cannot be null for update");
        }

        // Check if the unit exists
        if (!unitsRepository.existsById(unit.getId())) {
            throw new IllegalArgumentException("Unit with ID " + unit.getId() + " not found");
        }

        return unitsRepository.save(unit);
    }

    @Override
    public void deleteUnit(Long id) {
        if (!unitsRepository.existsById(id)) {
            throw new IllegalArgumentException("Unit with ID " + id + " not found");
        }
        unitsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean existsByUnitName(String unitName) {
        return unitsRepository.existsByUnitName(unitName);
    }

    @Override
    @Transactional
    public boolean existsByUnitAbbr(String unitAbbr) {
        return unitsRepository.existsByUnitAbbr(unitAbbr);
    }
}
