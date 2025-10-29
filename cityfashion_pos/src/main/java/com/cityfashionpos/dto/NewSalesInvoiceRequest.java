package com.cityfashionpos.dto;

import java.util.List;

import com.cityfashionpos.entity.TaxRateEntity;

public class NewSalesInvoiceRequest {
    private Long partyId;
    private String partyName;
    private String partyPhone;
    private List<NewSalesInvoiceItemRequest> items;
    private Double receivedAmount;
    private Double totalAmount;
    private Double discountAmount;
    private Double balanceAmount;
    private Double subtotalAmount;
    private String amountInWords;
    private Boolean isFullyReceived;
    private Double totalTaxAmount;
    private Double taxableAmount;

    public static class NewSalesInvoiceItemRequest {
        private Long id;
        private String itemName;
        private Integer quantity;
        private Double price;
        private Double discount;
        private Double discountAmount;
        private Double total;
        private Long itemId;
        private Long taxRateId; // Reference to selected tax rate
        private Double taxAmount;
        private Double taxPercent;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getDiscount() {
            return discount;
        }

        public void setDiscount(Double discount) {
            this.discount = discount;
        }

        public Double getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(Double discountAmount) {
            this.discountAmount = discountAmount;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long productId) {
            this.itemId = productId;
        }

        public Long getTaxRateId() {
            return taxRateId;
        }

        public void setTaxRateId(Long taxRateId) {
            this.taxRateId = taxRateId;
        }

        public Double getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(Double taxAmount) {
            this.taxAmount = taxAmount;
        }

        public Double getTaxPercent() {
            return taxPercent;
        }

        public void setTaxPercent(Double taxPercent) {
            this.taxPercent = taxPercent;
        }

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

    public String getPartyPhone() {
        return partyPhone;
    }

    public void setPartyPhone(String customerPhone) {
        this.partyPhone = customerPhone;
    }

    public List<NewSalesInvoiceItemRequest> getItems() {
        return items;
    }

    public void setItems(List<NewSalesInvoiceItemRequest> items) {
        this.items = items;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Boolean getIsFullyReceived() {
        return isFullyReceived;
    }

    public void setIsFullyReceived(Boolean isFullyReceived) {
        this.isFullyReceived = isFullyReceived;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Double getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(Double subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public Double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public Double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

}