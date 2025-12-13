package com.cityfashionpos.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "new_proforma_invoice")
public class NewProformaInvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "proforma_invoice_number")
    private String proformaInvoiceNumber;

    @Column(name = "proforma_invoice_date")
    private String proformaInvoiceDate;

    @Column(name = "party_id")
    private Long partyId;

    @Column(name = "total_amount")
    private Double totalAmount;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProformaInvoiceNumber() {
        return proformaInvoiceNumber;
    }

    public void setProformaInvoiceNumber(String proformaInvoiceNumber) {
        this.proformaInvoiceNumber = proformaInvoiceNumber;
    }

    public String getProformaInvoiceDate() {
        return proformaInvoiceDate;
    }

    public void setProformaInvoiceDate(String proformaInvoiceDate) {
        this.proformaInvoiceDate = proformaInvoiceDate;
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

}
