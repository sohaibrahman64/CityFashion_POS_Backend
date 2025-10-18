package com.cityfashionpos.service;

import java.util.List;
import java.util.Optional;

import com.cityfashionpos.entity.PartyEntity;

public interface PartyService {

    // Create a new party
    PartyEntity createParty(PartyEntity party);

    // Get all active parties
    List<PartyEntity> getAllParties();

    // Get all active parties with details
    List<PartyEntity> getAllActiveParties();

    // Get party by ID
    Optional<PartyEntity> getPartyById(Long id);

    // Update party
    PartyEntity updateParty(Long id, PartyEntity updatedParty);

    // Delete party (soft delete)
    void deleteParty(Long id);

    // Search parties by name
    List<PartyEntity> searchPartiesByName(String keyword);

    // Search parties by GSTIN
    Optional<PartyEntity> searchPartyByGstin(String gstin);

    // Search parties by phone number
    List<PartyEntity> searchPartiesByPhone(String phoneNumber);

    // Search parties by email
    List<PartyEntity> searchPartiesByEmail(String emailId);

    // Check if party name exists
    boolean existsByPartyName(String partyName);

    // Check if GSTIN exists
    boolean existsByGstin(String gstin);
}
