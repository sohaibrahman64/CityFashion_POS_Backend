package com.cityfashionpos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "proforma_invoice_transactions")
public class ProformaInvoiceTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_number", unique = true, nullable = false)
    private String transactionNumber;

    @Column(name = "proforma_invoice_id")
    private Long proformaInvoiceId;

    @Column(name = "proforma_invoice_number")
    private String proformaInvoiceNumber;

    @Column(name = "party_id")
    private Long partyId;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "transaction_time", nullable = false)
    private LocalTime transactionTime;

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
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "created_by")
    private String createdBy = "system";

    @Column(name = "updated_by")
    private String updatedBy = "system";

    @ManyToOne
    @JoinColumn(name = "proforma_invoice_id", insertable = false, updatable = false)
    private NewProformaInvoiceEntity proformaInvoiceEntity;

    @Column(name = "balance_amount")
    private BigDecimal balanceAmount;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Default constructor
    public ProformaInvoiceTransactionEntity() {
        this.transactionDate = LocalDate.now();
        this.transactionTime = LocalTime.now();

    }

    public ProformaInvoiceTransactionEntity(String transactionNumber, BigDecimal totalAmount) {
        this();
        this.transactionNumber = transactionNumber;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
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

    public Long getProformaInvoiceId() {
        return proformaInvoiceId;
    }

    public void setProformaInvoiceId(Long proformaInvoiceId) {
        this.proformaInvoiceId = proformaInvoiceId;
    }

    public String getProformaInvoiceNumber() {
        return proformaInvoiceNumber;
    }

    public void setProformaInvoiceNumber(String proformaInvoiceNumber) {
        this.proformaInvoiceNumber = proformaInvoiceNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public NewProformaInvoiceEntity getProformaInvoiceEntity() {
        return proformaInvoiceEntity;
    }

    public void setProformaInvoiceEntity(NewProformaInvoiceEntity proformaInvoiceEntity) {
        this.proformaInvoiceEntity = proformaInvoiceEntity;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

}
