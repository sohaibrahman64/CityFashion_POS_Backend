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
@Table(name = "payment_in_history_item")
public class PaymentInHistoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_in_history_id")
    private PaymentInHistoryEntity paymentInHistory;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "linked_amount")
    private BigDecimal linkedAmount;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_date")
    private String transactionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentInHistoryEntity getPaymentInHistory() {
        return paymentInHistory;
    }

    public void setPaymentInHistory(PaymentInHistoryEntity paymentInHistory) {
        this.paymentInHistory = paymentInHistory;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

}