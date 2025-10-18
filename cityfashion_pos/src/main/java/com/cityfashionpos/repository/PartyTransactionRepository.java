package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.PartyTransactionEntity;

@Repository
public interface PartyTransactionRepository extends JpaRepository<PartyTransactionEntity, Long> {

    List<PartyTransactionEntity> findByParty_Id(Long partyId);
}
