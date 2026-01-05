package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.util.List;

public class PaymentInHistoryResponse {
    private boolean success;
    private String message;
    private List<PaymentInHistoryItemResponse> paymentInHistoryItems;

    public static class PaymentInHistoryItemResponse {
        Long paymentInHistoryId;
        String referenceNumber;
        String transactionType;
        BigDecimal linkedAmount;
        String transactionDate;

        public Long getPaymentInHistoryId() {
            return paymentInHistoryId;
        }

        public void setPaymentInHistoryId(Long paymentInHistoryId) {
            this.paymentInHistoryId = paymentInHistoryId;
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

        public BigDecimal getLinkedAmount() {
            return linkedAmount;
        }

        public void setLinkedAmount(BigDecimal linkedAmount) {
            this.linkedAmount = linkedAmount;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
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

    public List<PaymentInHistoryItemResponse> getPaymentInHistoryItems() {
        return paymentInHistoryItems;
    }

    public void setPaymentInHistoryItems(List<PaymentInHistoryItemResponse> paymentInHistoryItems) {
        this.paymentInHistoryItems = paymentInHistoryItems;
    }
}
