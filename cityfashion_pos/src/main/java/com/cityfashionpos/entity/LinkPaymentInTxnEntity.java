package com.cityfashionpos.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "link_payment_in_txn")
public class LinkPaymentInTxnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_in_id")
    private NewPaymentInEntity newPaymentInEntity;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private PartyEntity partyEntity;

    @Column(name = "unused_amount")
    private BigDecimal unusedAmount;

    @Column(name = "received_amount")
    private BigDecimal receivedAmount;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnusedAmount() {
        return unusedAmount;
    }

    public void setUnusedAmount(BigDecimal unusedAmount) {
        this.unusedAmount = unusedAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public NewPaymentInEntity getNewPaymentInEntity() {
        return newPaymentInEntity;
    }

    public void setNewPaymentInEntity(NewPaymentInEntity newPaymentInEntity) {
        this.newPaymentInEntity = newPaymentInEntity;
    }

    public PartyEntity getPartyEntity() {
        return partyEntity;
    }

    public void setPartyEntity(PartyEntity newPartyEntity) {
        this.partyEntity = newPartyEntity;
    }

}
