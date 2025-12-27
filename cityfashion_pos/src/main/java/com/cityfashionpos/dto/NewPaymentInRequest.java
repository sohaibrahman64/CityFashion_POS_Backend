package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NewPaymentInRequest {
    private String partyName;
    private BigDecimal partyOpeningBalance;
    private BigDecimal partyUpdatedBalance;
    private Long partyId;
    private String receiptNumber;
    private BigDecimal receivedAmount;
    private Long paymentTypeId;
    private String receivedDate = LocalDate.now().toString();
    private String description;

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentType(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public BigDecimal getPartyOpeningBalance() {
        return partyOpeningBalance;
    }

    public void setPartyOpeningBalance(BigDecimal partyOpeningBalance) {
        this.partyOpeningBalance = partyOpeningBalance;
    }

    public BigDecimal getPartyUpdatedBalance() {
        return partyUpdatedBalance;
    }

    public void setPartyUpdatedBalance(BigDecimal partyUpdatedBalance) {
        this.partyUpdatedBalance = partyUpdatedBalance;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

}
