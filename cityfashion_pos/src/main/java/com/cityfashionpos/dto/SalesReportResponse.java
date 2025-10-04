package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cityfashionpos.model.TransactionType;

/**
 * DTO for sales report containing specific fields for sales transaction reports
 */
public class SalesReportResponse {

    private LocalDate date;
    private String invoiceNo;
    private String customerName;
    private TransactionType transactionType;
    private String paymentMode;
    private BigDecimal netAmount;
    private BigDecimal balanceAmount;

    // Default constructor
    public SalesReportResponse() {
    }

    // Constructor with all fields
    public SalesReportResponse(LocalDate date, String invoiceNo, String customerName,
            TransactionType transactionType, String paymentMode,
            BigDecimal netAmount, BigDecimal balanceAmount) {
        this.date = date;
        this.invoiceNo = invoiceNo;
        this.customerName = customerName;
        this.transactionType = transactionType;
        this.paymentMode = paymentMode;
        this.netAmount = netAmount;
        this.balanceAmount = balanceAmount;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @Override
    public String toString() {
        return "SalesReportResponse{" +
                "date=" + date +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", transactionType=" + transactionType +
                ", paymentMode='" + paymentMode + '\'' +
                ", netAmount=" + netAmount +
                ", balanceAmount=" + balanceAmount +
                '}';
    }
}
