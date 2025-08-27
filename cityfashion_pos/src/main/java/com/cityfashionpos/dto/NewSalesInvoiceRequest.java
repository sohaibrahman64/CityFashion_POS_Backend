package com.cityfashionpos.dto;

import java.util.List;

public class NewSalesInvoiceRequest {
    private String customerName;
    private String customerPhone;
    private List<NewSalesInvoiceItemRequest> items;
    private Double receivedAmount;
    private Double totalAmount;
    private Double discountAmount;
    private Double balanceAmount;
    private String amountInWords;
    private Boolean isFullyReceived;

    public static class NewSalesInvoiceItemRequest {
        private Long id;
        private String itemName;
        private Integer quantity;
        private Double price;
        private Double discount;
        private Double total;
        private Long productId;

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

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

}