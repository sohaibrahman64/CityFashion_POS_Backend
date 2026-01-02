package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.util.List;

public class LinkPaymentInResponse {
    private boolean success;
    private String message;
    private BigDecimal receivedAmount;
    private Long paymentInId;
    private Long partyId;
    private List<LinkedAmountItemResponse> linkedAmountItems;
    private BigDecimal unusedAmount;
    private Long linkPaymentInTxnId;

    public static class LinkedAmountItemResponse {
        Long partyTransactionId;
        BigDecimal linkedAmount;
        String referenceNumber;

        public Long getPartyTransactionId() {
            return partyTransactionId;
        }

        public void setPartyTransactionId(Long transactionId) {
            this.partyTransactionId = transactionId;
        }

        public BigDecimal getLinkedAmount() {
            return linkedAmount;
        }

        public void setLinkedAmount(BigDecimal linkedAmount) {
            this.linkedAmount = linkedAmount;
        }

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public void setReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
        }
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

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Long getPaymentInId() {
        return paymentInId;
    }

    public void setPaymentInId(Long paymentInId) {
        this.paymentInId = paymentInId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public List<LinkedAmountItemResponse> getLinkedAmountItems() {
        return linkedAmountItems;
    }

    public void setLinkedAmountItems(List<LinkedAmountItemResponse> linkedAmountItems) {
        this.linkedAmountItems = linkedAmountItems;
    }

    public BigDecimal getUnusedAmount() {
        return unusedAmount;
    }

    public void setUnusedAmount(BigDecimal unusedAmount) {
        this.unusedAmount = unusedAmount;
    }

    public Long getLinkPaymentInTxnId() {
        return linkPaymentInTxnId;
    }

    public void setLinkPaymentInTxnId(Long linkPaymentInTxnId) {
        this.linkPaymentInTxnId = linkPaymentInTxnId;
    }

}