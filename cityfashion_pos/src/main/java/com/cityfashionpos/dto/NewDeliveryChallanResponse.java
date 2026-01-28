package com.cityfashionpos.dto;

import java.time.LocalDate;
import java.util.List;

import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.TaxRateEntity;

public class NewDeliveryChallanResponse {
    private Long deliveryChallanId;
    private String deliveryChallanNumber;
    private LocalDate deliveryChallanInvoiceDate;
    private LocalDate deliveryChallanDueDate;
    private PartyEntity party;
    private String partyName;
    private String partyPhone;
    private List<NewDeliveryChallanResponse.NewDeliveryChallanItemResponse> items;
    private Double totalAmount;
    private Double totalDiscountAmount;
    private Double totalTaxAmount;
    private Double taxableAmount;
    private String amountInWords;
    private String message;
    private Boolean success;
    private Long totalQuantity;
    private String status;
    private String billingAddress;

    public static class NewDeliveryChallanItemResponse {
        private Long id;
        private Long itemId;
        private String itemName;
        private Integer quantity;
        private Double price;
        private Double discount;
        private Double discountAmount;
        private Double total;
        private Double taxAmount;
        private Double taxPercent;
        private TaxRateEntity taxRate;
        private String hsnCode;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
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

        public TaxRateEntity getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(TaxRateEntity taxRate) {
            this.taxRate = taxRate;
        }

        public String getHsnCode() {
            return hsnCode;
        }

        public void setHsnCode(String hsnCode) {
            this.hsnCode = hsnCode;
        }

    }

    // Getters and Setters for NewEstimateQuotationResponse fields

    public Long getDeliveryChallanId() {
        return deliveryChallanId;
    }

    public void setDeliveryChallanId(Long deliveryChallanId) {
        this.deliveryChallanId = deliveryChallanId;
    }

    public String getDeliveryChallanNumber() {
        return deliveryChallanNumber;
    }

    public void setDeliveryChallanNumber(String deliveryChallanNumber) {
        this.deliveryChallanNumber = deliveryChallanNumber;
    }

    public LocalDate getDeliveryChallanInvoiceDate() {
        return deliveryChallanInvoiceDate;
    }

    public void setDeliveryChallanInvoiceDate(LocalDate estimateQuotationDate) {
        this.deliveryChallanInvoiceDate = estimateQuotationDate;
    }

    public PartyEntity getParty() {
        return party;
    }

    public void setParty(PartyEntity party) {
        this.party = party;
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

    public void setPartyPhone(String partyPhone) {
        this.partyPhone = partyPhone;
    }

    public List<NewDeliveryChallanItemResponse> getItems() {
        return items;
    }

    public void setItems(List<NewDeliveryChallanItemResponse> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(Double totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
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

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeliveryChallanDueDate() {
        return deliveryChallanDueDate;
    }

    public void setDeliveryChallanDueDate(LocalDate deliveryChallanDueDate) {
        this.deliveryChallanDueDate = deliveryChallanDueDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
