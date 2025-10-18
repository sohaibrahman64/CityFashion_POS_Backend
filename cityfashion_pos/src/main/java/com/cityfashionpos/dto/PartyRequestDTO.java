package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PartyRequestDTO {

    private String partyName;
    private String gstin;
    private String phoneNumber;
    private String gstType;
    private String state;
    private String emailId;
    private String billingAddress;
    private String shippingAddress;
    private Boolean enableShipping;
    private Long gstTypeId;
    private Long stateId;

    // Credit & Balance fields
    private BigDecimal openingBalance;
    private LocalDate asOfDate;
    private String paymentType; // "toPay" or "toReceive"
    private String creditLimitType; // "noLimit" or "customLimit"
    private BigDecimal customLimit;

    // Constructors
    public PartyRequestDTO() {
    }

    // Getters and Setters
    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGstType() {
        return gstType;
    }

    public void setGstType(String gstType) {
        this.gstType = gstType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Boolean getEnableShipping() {
        return enableShipping;
    }

    public void setEnableShipping(Boolean enableShipping) {
        this.enableShipping = enableShipping;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public LocalDate getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(LocalDate asOfDate) {
        this.asOfDate = asOfDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCreditLimitType() {
        return creditLimitType;
    }

    public void setCreditLimitType(String creditLimitType) {
        this.creditLimitType = creditLimitType;
    }

    public BigDecimal getCustomLimit() {
        return customLimit;
    }

    public void setCustomLimit(BigDecimal customLimit) {
        this.customLimit = customLimit;
    }

    public Long getGstTypeId() {
        return gstTypeId;
    }

    public void setGstTypeId(Long gstTypeId) {
        this.gstTypeId = gstTypeId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
