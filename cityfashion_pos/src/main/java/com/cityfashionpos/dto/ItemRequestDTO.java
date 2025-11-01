package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemRequestDTO {
    private String name;
    private String hsn;
    private String unit;
    private String category;
    private String code;
    private PricingDTO pricing;
    private PurchaseTaxesDTO purchasePriceTaxes;
    private StockDTO stock;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PricingDTO getPricing() {
        return pricing;
    }

    public void setPricing(PricingDTO pricing) {
        this.pricing = pricing;
    }

    public PurchaseTaxesDTO getPurchasePriceTaxes() {
        return purchasePriceTaxes;
    }

    public void setPurchasePriceTaxes(PurchaseTaxesDTO purchasePriceTaxes) {
        this.purchasePriceTaxes = purchasePriceTaxes;
    }

    public StockDTO getStock() {
        return stock;
    }

    public void setStock(StockDTO stock) {
        this.stock = stock;
    }

    // Inner DTOs
    public static class PricingDTO {
        private BigDecimal salePrice;
        private String salePriceType;
        private BigDecimal discountAmount;
        private String discountType;

        public BigDecimal getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
        }

        public String getSalePriceType() {
            return salePriceType;
        }

        public void setSalePriceType(String salePriceType) {
            this.salePriceType = salePriceType;
        }

        public BigDecimal getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
        }

        public String getDiscountType() {
            return discountType;
        }

        public void setDiscountType(String discountType) {
            this.discountType = discountType;
        }
    }

    public static class PurchaseTaxesDTO {
        private BigDecimal purchasePrice;
        private String purchasePriceType;
        private Long taxRateId;
        private Long taxRateIndex;

        public BigDecimal getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(BigDecimal purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public String getPurchasePriceType() {
            return purchasePriceType;
        }

        public void setPurchasePriceType(String purchasePriceType) {
            this.purchasePriceType = purchasePriceType;
        }

        public Long getTaxRateId() {
            return taxRateId;
        }

        public void setTaxRateId(Long taxRateId) {
            this.taxRateId = taxRateId;
        }

        public Long getTaxRateIndex() {
            return taxRateIndex;
        }

        public void setTaxRateIndex(Long taxRateIndex) {
            this.taxRateIndex = taxRateIndex;
        }
    }

    public static class StockDTO {
        private Integer openingQuantity;
        private BigDecimal atPrice;
        private LocalDate asOfDate;
        private Integer minStock;
        private String location;

        public Integer getOpeningQuantity() {
            return openingQuantity;
        }

        public void setOpeningQuantity(Integer openingQuantity) {
            this.openingQuantity = openingQuantity;
        }

        public BigDecimal getAtPrice() {
            return atPrice;
        }

        public void setAtPrice(BigDecimal atPrice) {
            this.atPrice = atPrice;
        }

        public LocalDate getAsOfDate() {
            return asOfDate;
        }

        public void setAsOfDate(LocalDate asOfDate) {
            this.asOfDate = asOfDate;
        }

        public Integer getMinStock() {
            return minStock;
        }

        public void setMinStock(Integer minStock) {
            this.minStock = minStock;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}