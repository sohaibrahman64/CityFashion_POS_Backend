package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class DeliveryChallanTransactionRequest {
    private String transactionNumber;

    private String deliveryChallanNumber;

    private Long deliveryChallanId;

    private LocalDate deliveryChallanInvoiceDate;

    private LocalDate deliveryChallanDueDate;

    private String billingAddress;

    private Long partyId;

    private String partyName;

    private LocalDate transactionDate;

    private LocalTime transactionTime;

    private BigDecimal totalAmount;

    private BigDecimal taxAmount = BigDecimal.ZERO;

    private BigDecimal discountAmount;

    private Integer itemCount = 0;

    private BigDecimal totalQuantity = BigDecimal.ZERO;

    private String notes;

    private String createdBy;

    public DeliveryChallanTransactionRequest() {
        this.transactionDate = LocalDate.now();
        this.transactionTime = LocalTime.now();
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getDeliveryChallanNumber() {
        return deliveryChallanNumber;
    }

    public void setDeliveryChallanNumber(String deliveryChallanNumber) {
        this.deliveryChallanNumber = deliveryChallanNumber;
    }

    public Long getDeliveryChallanId() {
        return deliveryChallanId;
    }

    public void setDeliveryChallanId(Long deliveryChallanInvoiceId) {
        this.deliveryChallanId = deliveryChallanInvoiceId;
    }

    public LocalDate getDeliveryChallanInvoiceDate() {
        return deliveryChallanInvoiceDate;
    }

    public void setDeliveryChallanInvoiceDate(LocalDate deliveryChallanInvoiceDate) {
        this.deliveryChallanInvoiceDate = deliveryChallanInvoiceDate;
    }

    public LocalDate getDeliveryChallanDueDate() {
        return deliveryChallanDueDate;
    }

    public void setDeliveryChallanDueDate(LocalDate deliveryChallanDueDate) {
        this.deliveryChallanDueDate = deliveryChallanDueDate;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

}
