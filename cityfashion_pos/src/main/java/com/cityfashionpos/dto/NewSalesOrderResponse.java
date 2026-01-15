package com.cityfashionpos.dto;

import java.time.LocalDate;
import java.util.List;

import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.TaxRateEntity;

public class NewSalesOrderResponse {
    private Long salesOrderId;
    private String salesOrderNumber;
    private LocalDate orderDate;
    private LocalDate dueDate;
    private PartyEntity party;
    private String partyName;
    private String partyPhone;
    private List<NewSalesOrderItemResponse> items;
    private Double totalAmount;
    private Double totalDiscountAmount;
    private Double totalTaxAmount;
    private Double taxableAmount;
    private Double advanceAmount;
    private Double balanceAmount;
    private String amountInWords;
    private String message;
    private Boolean success;
    private Long totalQuantity;
    private String status;

    public static class NewSalesOrderItemResponse {
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

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long estimateQuotationId) {
        this.salesOrderId = estimateQuotationId;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String estimateQuotationNumber) {
        this.salesOrderNumber = estimateQuotationNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate estimateQuotationDate) {
        this.orderDate = estimateQuotationDate;
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

    public List<NewSalesOrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<NewSalesOrderItemResponse> items) {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

}