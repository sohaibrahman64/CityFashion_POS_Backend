package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DeliveryChallanTransactionResponse {
    private Long id;
    private String transactionNumber;
    private LocalDate transactionDate;
    private Long deliveryChallanId;
    private String deliveryChallanNumber;
    private Long partyId;
    private String partyName;
    private LocalDate deliveryChallanInvoiceDate;
    private LocalDate deliveryChallanDueDate;
    private String billingAddress;
    private BigDecimal totalAmount;
    private BigDecimal taxAmount;
    private BigDecimal discountAmount;
    private Integer itemCount;
    private BigDecimal totalQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private String notes;
    private String status;

    boolean success = true;
    private String message;

    public DeliveryChallanTransactionResponse() {

    }

    public DeliveryChallanTransactionResponse(boolean success, String message) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Long getDeliveryChallanId() {
        return deliveryChallanId;
    }

    public void setDeliveryChallanId(Long deliveryChallanInvoiceId) {
        this.deliveryChallanId = deliveryChallanInvoiceId;
    }

    public String getDeliveryChallanNumber() {
        return deliveryChallanNumber;
    }

    public void setDeliveryChallanNumber(String deliveryChallanInvoiceNumber) {
        this.deliveryChallanNumber = deliveryChallanInvoiceNumber;
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

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

}
