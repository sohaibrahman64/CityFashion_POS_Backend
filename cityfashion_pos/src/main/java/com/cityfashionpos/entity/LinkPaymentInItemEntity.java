package com.cityfashionpos.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "link_payment_in_items")
public class LinkPaymentInItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "link_payment_in_id")
    private LinkPaymentInTxnEntity linkPaymentInTxnEntity;

    @ManyToOne
    @JoinColumn(name = "party_transaction_id")
    private PartyTransactionEntity partyTransactionEntity;

    private String referenceNumber;
    private BigDecimal linkedAmount;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinkPaymentInTxnEntity getLinkPaymentInTxnEntity() {
        return linkPaymentInTxnEntity;
    }

    public void setLinkPaymentInTxnEntity(LinkPaymentInTxnEntity linkPaymentInEntity) {
        this.linkPaymentInTxnEntity = linkPaymentInEntity;
    }

    public PartyTransactionEntity getPartyTransactionEntity() {
        return partyTransactionEntity;
    }

    public void setPartyTransactionEntity(PartyTransactionEntity partyTransactionEntity) {
        this.partyTransactionEntity = partyTransactionEntity;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public BigDecimal getLinkedAmount() {
        return linkedAmount;
    }

    public void setLinkedAmount(BigDecimal linkedAmount) {
        this.linkedAmount = linkedAmount;
    }
}