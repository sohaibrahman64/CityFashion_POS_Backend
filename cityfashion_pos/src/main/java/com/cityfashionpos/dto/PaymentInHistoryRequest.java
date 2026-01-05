package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.util.List;

public class PaymentInHistoryRequest {
    Long linkPaymentInTxnId;
    List<PaymentInHistoryRequestItem> paymentInHistoryRequestItems;

    public static class PaymentInHistoryRequestItem {
        String referenceNumber;
        BigDecimal linkedAmount;
        String transactionType;
        String transactionDate;

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public void setReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
        }

        public BigDecimal getLinkedAmount() {
            return linkedAmount;
        }

        public void setLinkedAmount(BigDecimal linkedAmount) {
            this.linkedAmount = linkedAmount;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }
    }

    public List<PaymentInHistoryRequestItem> getPaymentInHistoryRequestItems() {
        return paymentInHistoryRequestItems;
    }

    public void setPaymentInHistoryRequestItems(List<PaymentInHistoryRequestItem> paymentInHistoryRequestItems) {
        this.paymentInHistoryRequestItems = paymentInHistoryRequestItems;
    }

    public Long getLinkPaymentInTxnId() {
        return linkPaymentInTxnId;
    }

    public void setLinkPaymentInTxnId(Long linkPaymentInTxnId) {
        this.linkPaymentInTxnId = linkPaymentInTxnId;
    }

}
