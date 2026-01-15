
package com.cityfashionpos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.LinkPaymentInItemEntity;

@Repository
public interface LinkPaymentInItemRepository extends JpaRepository<LinkPaymentInItemEntity, Long> {

    @Query("SELECT item FROM LinkPaymentInItemEntity item WHERE item.linkPaymentInTxnEntity.id = :linkPaymentInTxnId")
    List<LinkPaymentInItemEntity> findByLinkPaymentInTxnId(Long linkPaymentInTxnId);

    @Query("SELECT item FROM LinkPaymentInItemEntity item WHERE item.partyTransactionEntity.id = :partyTransactionId")
    List<LinkPaymentInItemEntity> findByPartyTransactionId(Long partyTransactionId);
}