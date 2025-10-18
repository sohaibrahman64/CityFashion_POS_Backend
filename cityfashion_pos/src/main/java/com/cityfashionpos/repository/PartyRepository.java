package com.cityfashionpos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PartyEntity;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Long> {

    // Find all active parties
    List<PartyEntity> findByIsActiveTrue();

    // Find party by party name (case-insensitive)
    Optional<PartyEntity> findByPartyNameIgnoreCase(String partyName);

    // Search parties by name containing keyword
    List<PartyEntity> findByPartyNameContainingIgnoreCaseAndIsActiveTrue(String keyword);

    // Find parties by GSTIN
    Optional<PartyEntity> findByGstin(String gstin);

    // Find parties by phone number
    List<PartyEntity> findByPhoneNumberAndIsActiveTrue(String phoneNumber);

    // Find parties by email
    List<PartyEntity> findByEmailIdAndIsActiveTrue(String emailId);

    // Check if party name exists (for validation)
    boolean existsByPartyNameIgnoreCase(String partyName);

    // Check if GSTIN exists (for validation)
    boolean existsByGstin(String gstin);

    // Custom query to get all parties with related entities
    @Query("SELECT p FROM PartyEntity p LEFT JOIN FETCH p.gstType LEFT JOIN FETCH p.state WHERE p.isActive = true")
    List<PartyEntity> findAllActivePartiesWithDetails();
}
