package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PartyResponseDTO {

    private Long id;
    private String partyName;
    private String gstin;
    private String phoneNumber;
    private GstTypeInfo gstType;
    private StateInfo state;
    private String emailId;
    private String billingAddress;
    private String shippingAddress;
    private Boolean enableShipping;

    // Credit & Balance fields
    private BigDecimal openingBalance;
    private BigDecimal updatedBalance;
    private LocalDate asOfDate;
    private String paymentType;
    private String creditLimitType;
    private BigDecimal customLimit;

    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Nested DTOs for related entities
    public static class GstTypeInfo {
        private Long id;
        private String gstType;

        public GstTypeInfo() {
        }

        public GstTypeInfo(Long id, String gstType) {
            this.id = id;
            this.gstType = gstType;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getGstType() {
            return gstType;
        }

        public void setGstType(String gstType) {
            this.gstType = gstType;
        }
    }

    public static class StateInfo {
        private Long id;
        private String state;

        public StateInfo() {
        }

        public StateInfo(Long id, String state) {
            this.id = id;
            this.state = state;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    // Constructors
    public PartyResponseDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public GstTypeInfo getGstType() {
        return gstType;
    }

    public void setGstType(GstTypeInfo gstType) {
        this.gstType = gstType;
    }

    public StateInfo getState() {
        return state;
    }

    public void setState(StateInfo state) {
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public BigDecimal getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(BigDecimal updatedBalance) {
        this.updatedBalance = updatedBalance;
    }
}
