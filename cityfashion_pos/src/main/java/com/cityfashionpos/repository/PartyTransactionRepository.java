package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PartyTransactionEntity;

@Repository
public interface PartyTransactionRepository extends JpaRepository<PartyTransactionEntity, Long> {

    List<PartyTransactionEntity> findByParty_Id(Long partyId);

    @Query("SELECT p FROM PartyTransactionEntity p WHERE p.party.id = :partyId AND p.status IN ('PARTIAL','UNPAID', 'RECEIVABLE_OPENING_BALANCE')")
    List<PartyTransactionEntity> findPartialOrUnpaid(Long partyId);
}
