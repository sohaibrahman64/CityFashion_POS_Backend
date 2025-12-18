package com.cityfashionpos.dto;

import java.math.BigDecimal;

public class NewPaymentInResponse {
    private PartyInfo partyInfo;
    private PaymentTypeInfo paymentTypeInfo;
    private String receiptNumber;
    private BigDecimal receivedAmount;
    private String paymentType;
    private String receivedDate;
    private String description;
    private boolean success;
    private String message;

    public PaymentTypeInfo getPaymentTypeInfo() {
        return paymentTypeInfo;
    }

    public void setPaymentTypeInfo(PaymentTypeInfo paymentTypeInfo) {
        this.paymentTypeInfo = paymentTypeInfo;
    }

    public static class PartyInfo {
        Long partyId;
        String partyName;
        Double partyBalance;
        String partyPhone;
        String partyAddress;
        String partyState;

        public PartyInfo() {
        }

        public PartyInfo(Long partyId, String partyName, Double partyBalance, String partyPhone, String partyAddress,
                String partyState) {
            this.partyId = partyId;
            this.partyName = partyName;
            this.partyBalance = partyBalance;
            this.partyPhone = partyPhone;
            this.partyAddress = partyAddress;
            this.partyState = partyState;
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

        public Double getPartyBalance() {
            return partyBalance;
        }

        public void setPartyBalance(Double partyBalance) {
            this.partyBalance = partyBalance;
        }

        public String getPartyPhone() {
            return partyPhone;
        }

        public void setPartyPhone(String partyPhone) {
            this.partyPhone = partyPhone;
        }

        public String getPartyAddress() {
            return partyAddress;
        }

        public void setPartyAddress(String partyAddress) {
            this.partyAddress = partyAddress;
        }

        public String getPartyState() {
            return partyState;
        }

        public void setPartyState(String partyState) {
            this.partyState = partyState;
        }

    }

    public static class PaymentTypeInfo {
        Long paymentTypeId;
        String paymentType;

        public PaymentTypeInfo() {
        }

        public PaymentTypeInfo(Long paymentTypeId, String paymentType) {
            this.paymentTypeId = paymentTypeId;
            this.paymentType = paymentType;
        }

        public Long getPaymentTypeId() {
            return paymentTypeId;
        }

        public void setPaymentTypeId(Long paymentTypeId) {
            this.paymentTypeId = paymentTypeId;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

    }

    public PartyInfo getPartyInfo() {
        return partyInfo;
    }

    public void setPartyInfo(PartyInfo partyInfo) {
        this.partyInfo = partyInfo;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
