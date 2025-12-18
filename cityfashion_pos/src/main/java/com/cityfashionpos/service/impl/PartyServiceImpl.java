package com.cityfashionpos.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Override
    @Transactional
    public PartyEntity createParty(PartyEntity party) {
        // Validate party name
        if (party.getPartyName() == null || party.getPartyName().trim().isEmpty()) {
            throw new IllegalArgumentException("Party name is required.");
        }

        // Check if party name already exists
        if (partyRepository.existsByPartyNameIgnoreCase(party.getPartyName())) {
            throw new IllegalArgumentException("Party with this name already exists.");
        }

        // Check if GSTIN already exists (if provided)
        if (party.getGstin() != null && !party.getGstin().trim().isEmpty()) {
            if (partyRepository.existsByGstin(party.getGstin())) {
                throw new IllegalArgumentException("GSTIN already exists.");
            }
        }

        // Set timestamps
        party.setCreatedAt(LocalDateTime.now().toString());
        party.setUpdatedAt(LocalDateTime.now().toString());

        // Set default values if not provided
        if (party.getIsActive() == null) {
            party.setIsActive(true);
        }
        if (party.getEnableShipping() == null) {
            party.setEnableShipping(false);
        }
        if (party.getPaymentType() == null || party.getPaymentType().isEmpty()) {
            party.setPaymentType("toPay");
        }
        if (party.getCreditLimitType() == null || party.getCreditLimitType().isEmpty()) {
            party.setCreditLimitType("noLimit");
        }

        return partyRepository.save(party);
    }

    @Override
    public List<PartyEntity> getAllParties() {
        return partyRepository.findAll();
    }

    @Override
    public List<PartyEntity> getAllActiveParties() {
        return partyRepository.findAllActivePartiesWithDetails();
    }

    @Override
    public Optional<PartyEntity> getPartyById(Long id) {
        return partyRepository.findById(id);
    }

    @Override
    @Transactional
    public PartyEntity updateParty(Long id, PartyEntity updatedParty) {
        PartyEntity existingParty = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with id: " + id));

        // Validate party name if changed
        if (!existingParty.getPartyName().equalsIgnoreCase(updatedParty.getPartyName())) {
            if (partyRepository.existsByPartyNameIgnoreCase(updatedParty.getPartyName())) {
                throw new IllegalArgumentException("Party with this name already exists.");
            }
        }

        // Validate GSTIN if changed
        if (updatedParty.getGstin() != null && !updatedParty.getGstin().trim().isEmpty()) {
            if (!updatedParty.getGstin().equals(existingParty.getGstin())) {
                if (partyRepository.existsByGstin(updatedParty.getGstin())) {
                    throw new IllegalArgumentException("GSTIN already exists.");
                }
            }
        }

        // Update fields
        existingParty.setPartyName(updatedParty.getPartyName());
        existingParty.setGstin(updatedParty.getGstin());
        existingParty.setPhoneNumber(updatedParty.getPhoneNumber());
        existingParty.setGstType(updatedParty.getGstType());
        existingParty.setState(updatedParty.getState());
        existingParty.setEmailId(updatedParty.getEmailId());
        existingParty.setBillingAddress(updatedParty.getBillingAddress());
        existingParty.setShippingAddress(updatedParty.getShippingAddress());
        existingParty.setEnableShipping(updatedParty.getEnableShipping());
        existingParty.setOpeningBalance(updatedParty.getOpeningBalance());
        existingParty.setAsOfDate(updatedParty.getAsOfDate());
        existingParty.setPaymentType(updatedParty.getPaymentType());
        existingParty.setCreditLimitType(updatedParty.getCreditLimitType());
        existingParty.setCustomLimit(updatedParty.getCustomLimit());
        existingParty.setUpdatedAt(LocalDateTime.now().toString());

        return partyRepository.save(existingParty);
    }

    @Override
    @Transactional
    public void deleteParty(Long id) {
        PartyEntity party = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with id: " + id));

        // Soft delete
        party.setIsActive(false);
        party.setUpdatedAt(LocalDateTime.now().toString());
        partyRepository.save(party);
    }

    @Override
    public List<PartyEntity> searchPartiesByName(String keyword) {
        return partyRepository.findByPartyNameContainingIgnoreCaseAndIsActiveTrue(keyword);
    }

    @Override
    public Optional<PartyEntity> searchPartyByGstin(String gstin) {
        return partyRepository.findByGstin(gstin);
    }

    @Override
    public List<PartyEntity> searchPartiesByPhone(String phoneNumber) {
        return partyRepository.findByPhoneNumberAndIsActiveTrue(phoneNumber);
    }

    @Override
    public List<PartyEntity> searchPartiesByEmail(String emailId) {
        return partyRepository.findByEmailIdAndIsActiveTrue(emailId);
    }

    @Override
    public boolean existsByPartyName(String partyName) {
        return partyRepository.existsByPartyNameIgnoreCase(partyName);
    }

    @Override
    public boolean existsByGstin(String gstin) {
        return partyRepository.existsByGstin(gstin);
    }
}
