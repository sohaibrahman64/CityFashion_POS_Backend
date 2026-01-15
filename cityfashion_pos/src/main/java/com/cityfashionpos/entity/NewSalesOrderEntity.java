package com.cityfashionpos.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "new_sales_order")
public class NewSalesOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sales_order_number")
    private String salesOrderNumber;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "party_id")
    private Long partyId;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "advance_amount")
    private Double advanceAmount;

    @Column(name = "balance_amount")
    private Double balanceAmount;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "total_tax_amount")
    private Double totalTaxAmount;

    @Column(name = "taxable_amount")
    private Double taxableAmount;

    @Column(name = "amount_in_words")
    private String amountInWords;

    @Column(name = "message")
    private String message;

    @Column(name = "success")
    private Boolean success;

    @Column(name = "total_quantity")
    private Long totalQuantity;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private String createdAt = LocalDateTime.now().toString();

    @Column(name = "updated_at")
    private String updatedAt = LocalDateTime.now().toString();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String proformaInvoiceNumber) {
        this.salesOrderNumber = proformaInvoiceNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String proformaInvoiceDate) {
        this.orderDate = proformaInvoiceDate;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public Double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

}
