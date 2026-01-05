package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.util.List;

public class LinkPaymentInRequest {
    PartyResponseDTO party;
    BigDecimal receivedAmount;
    List<LinkedAmountItem> linkedAmountItems;
    BigDecimal unusedAmount;
    Long paymentInId;
    Long partyId;

    public static class LinkedAmountItem {
        Long partyTransactionId;
        BigDecimal linkedAmount;
        String referenceNumber;
        String transactionType;
        String transactionDate;

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

    public PartyResponseDTO getParty() {
        return party;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public List<LinkedAmountItem> getLinkedAmountItems() {
        return linkedAmountItems;
    }

    public BigDecimal getUnusedAmount() {
        return unusedAmount;
    }

    public void setParty(PartyResponseDTO party) {
        this.party = party;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public void setLinkedAmountItems(List<LinkedAmountItem> linkedAmountItems) {
        this.linkedAmountItems = linkedAmountItems;
    }

    public void setUnusedAmount(BigDecimal unusedAmount) {
        this.unusedAmount = unusedAmount;
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
}