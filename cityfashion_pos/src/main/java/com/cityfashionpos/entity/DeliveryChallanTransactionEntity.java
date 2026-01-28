package com.cityfashionpos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_challan_transactions")
public class DeliveryChallanTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_number", unique = true, nullable = false)
    private String transactionNumber;

    @Column(name = "delivery_challan_number")
    private String deliveryChallanNumber;

    @Column(name = "transaction_date")
    private String transactionDate = LocalDate.now().toString();

    @Column(name = "transaction_time")
    private String transactionTime = LocalDateTime.now().toLocalTime().toString();

    @Column(name = "delivery_challan_invoice_date")
    private String deliveryChallanInvoiceDate;

    @Column(name = "delivery_challan_due_date")
    private String deliveryChallanDueDate;

    @Column(name = "party_id")
    private Long partyId;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "billing_address", length = 1000)
    private String billingAddress;

    @Column(name = "total_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "tax_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(name = "discount_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount = 0;

    @Column(name = "total_quantity", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalQuantity = BigDecimal.ZERO;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "status", nullable = false)
    private String status = "OPEN";

    @Column(name = "created_at", nullable = false, updatable = false)
    private String createdAt = LocalDateTime.now().toString();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = LocalDateTime.now().toString();

    @Column(name = "created_by")
    private String createdBy = "system";

    @Column(name = "updated_by")
    private String updatedBy = "system";

    @ManyToOne
    @JoinColumn(name = "delivery_challan_id")
    private NewDeliveryChallanEntity deliveryChallanEntity;

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

    public String getDeliveryChallanNumber() {
        return deliveryChallanNumber;
    }

    public void setDeliveryChallanNumber(String deliveryChallanNumber) {
        this.deliveryChallanNumber = deliveryChallanNumber;
    }

    public String getDeliveryChallanInvoiceDate() {
        return deliveryChallanInvoiceDate;
    }

    public void setDeliveryChallanInvoiceDate(String deliveryChallanInvoiceDate) {
        this.deliveryChallanInvoiceDate = deliveryChallanInvoiceDate;
    }

    public String getDeliveryChallanDueDate() {
        return deliveryChallanDueDate;
    }

    public void setDeliveryChallanDueDate(String deliveryChallanDueDate) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public NewDeliveryChallanEntity getDeliveryChallanEntity() {
        return deliveryChallanEntity;
    }

    public void setDeliveryChallanEntity(NewDeliveryChallanEntity deliveryChallanEntity) {
        this.deliveryChallanEntity = deliveryChallanEntity;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

}
