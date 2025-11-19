package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for parties report containing specific fields for party transaction
 * reports
 */
public class PartiesReportResponse {

    private LocalDateTime date;
    private String partyName;
    private String invoiceNumber;
    private String purchaseBillNumber;
    private String referenceNumber;
    private String transactionType;
    private BigDecimal partyTotal;
    private BigDecimal partyBalance;
    private String status;
    private Long id;
    private Long invoiceId;

    // Default constructor
    public PartiesReportResponse() {
    }

    // Constructor with all fields
    public PartiesReportResponse(LocalDateTime date, String partyName, String invoiceNumber,
            String purchaseBillNumber, String referenceNumber, String transactionType,
            BigDecimal partyTotal, BigDecimal partyBalance, String status) {
        this.date = date;
        this.partyName = partyName;
        this.invoiceNumber = invoiceNumber;
        this.purchaseBillNumber = purchaseBillNumber;
        this.referenceNumber = referenceNumber;
        this.transactionType = transactionType;
        this.partyTotal = partyTotal;
        this.partyBalance = partyBalance;
        this.status = status;
    }

    // Getters and Setters
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPurchaseBillNumber() {
        return purchaseBillNumber;
    }

    public void setPurchaseBillNumber(String purchaseBillNumber) {
        this.purchaseBillNumber = purchaseBillNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getPartyTotal() {
        return partyTotal;
    }

    public void setPartyTotal(BigDecimal partyTotal) {
        this.partyTotal = partyTotal;
    }

    public BigDecimal getPartyBalance() {
        return partyBalance;
    }

    public void setPartyBalance(BigDecimal partyBalance) {
        this.partyBalance = partyBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "PartiesReportResponse{" +
                "date=" + date +
                ", partyName='" + partyName + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", purchaseBillNumber='" + purchaseBillNumber + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", transactionType=" + transactionType +
                ", partyTotal=" + partyTotal +
                ", partyBalance=" + partyBalance +
                ", status='" + status + '\'' +
                '}';
    }
}
