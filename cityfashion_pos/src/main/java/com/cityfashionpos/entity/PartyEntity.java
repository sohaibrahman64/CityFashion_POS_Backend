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
@Table(name = "parties")
public class PartyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "party_name", nullable = false)
    private String partyName;

    @Column(name = "gstin")
    private String gstin;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "gst_type_id")
    private GstTypeEntity gstType;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateEntity state;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "billing_address", columnDefinition = "TEXT")
    private String billingAddress;

    @Column(name = "shipping_address", columnDefinition = "TEXT")
    private String shippingAddress;

    @Column(name = "enable_shipping")
    private Boolean enableShipping = false;

    // Credit & Balance fields
    @Column(name = "opening_balance", precision = 15, scale = 2)
    private BigDecimal openingBalance;

    @Column(name = "as_of_date")
    private String asOfDate;

    @Column(name = "payment_type")
    private String paymentType; // "toPay" or "toReceive"

    @Column(name = "credit_limit_type")
    private String creditLimitType; // "noLimit" or "customLimit"

    @Column(name = "custom_limit", precision = 15, scale = 2)
    private BigDecimal customLimit;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private String createdAt = LocalDateTime.now().toString();

    @Column(name = "updated_at")
    private String updatedAt = LocalDateTime.now().toString();

    @Column(name = "updated_balance")
    private BigDecimal updatedBalance;

    // Constructors
    public PartyEntity() {
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

    public GstTypeEntity getGstType() {
        return gstType;
    }

    public void setGstType(GstTypeEntity gstType) {
        this.gstType = gstType;
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
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

    public String getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(String asOfDate) {
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

    public BigDecimal getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(BigDecimal updatedBalance) {
        this.updatedBalance = updatedBalance;
    }

}
