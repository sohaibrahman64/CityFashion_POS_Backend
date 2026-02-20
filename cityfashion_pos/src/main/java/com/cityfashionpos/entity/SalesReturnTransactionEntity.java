package com.cityfashionpos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cityfashionpos.model.TransactionType;

@Entity
@Table(name = "sales_return_transactions")
public class SalesReturnTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sales_return_transaction_number", unique = true, nullable = false)
    private String salesReturnTransactionNumber;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "transaction_date", nullable = false)
    private String transactionDate = LocalDate.now().toString();

    @Column(name = "transaction_time", nullable = false)
    private String transactionTime = LocalTime.now().toString();;

    @Column(name = "total_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "paid_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Column(name = "tax_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(name = "discount_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "balance_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus = "UNPAID";

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "notes")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType = TransactionType.SALES_RETURN;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount = 0;

    @Column(name = "total_quantity", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalQuantity = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false, updatable = false)
    private String createdAt = LocalDateTime.now().toString();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = LocalDateTime.now().toString();

    @Column(name = "created_by")
    private String createdBy = "system";

    @Column(name = "updated_by")
    private String updatedBy = "system";

    // Relationships
    @ManyToOne
    @JoinColumn(name = "party_id")
    private PartyEntity partyEntity;

    @ManyToOne
    @JoinColumn(name = "sales_return_id")
    private NewSalesReturnEntity salesReturnEntity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private NewSalesInvoiceEntity newSalesInvoiceEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesReturnTransactionNumber() {
        return salesReturnTransactionNumber;
    }

    public void setSalesReturnTransactionNumber(String salesReturnTransactionNumber) {
        this.salesReturnTransactionNumber = salesReturnTransactionNumber;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String salesReturnTransactionDate) {
        this.transactionDate = salesReturnTransactionDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
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

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

    public PartyEntity getPartyEntity() {
        return partyEntity;
    }

    public void setPartyEntity(PartyEntity partyEntity) {
        this.partyEntity = partyEntity;
    }

    public NewSalesReturnEntity getSalesReturnEntity() {
        return salesReturnEntity;
    }

    public void setSalesReturnEntity(NewSalesReturnEntity salesReturnEntity) {
        this.salesReturnEntity = salesReturnEntity;
    }

    public NewSalesInvoiceEntity getNewSalesInvoiceEntity() {
        return newSalesInvoiceEntity;
    }

    public void setNewSalesInvoiceEntity(NewSalesInvoiceEntity newSalesInvoiceEntity) {
        this.newSalesInvoiceEntity = newSalesInvoiceEntity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

}
