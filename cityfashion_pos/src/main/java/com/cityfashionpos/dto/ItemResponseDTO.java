package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cityfashionpos.entity.TaxRateEntity;

public class ItemResponseDTO {
    // Add fields, constructors, getters, and setters as needed
    Long id;
    String code;
    String name;
    String hsn;
    String unit;
    String category;
    BigDecimal salePrice;
    String salePriceType;
    BigDecimal discountAmount;
    String discountType;
    BigDecimal purchasePrice;
    String purchasePriceType;
    TaxRateEntity taxRate;
    BigDecimal price;
    Integer openingQuantity;
    BigDecimal atPrice;
    LocalDate asOfDate;
    Integer minStock;
    String location;
    String imagePath;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public TaxRateEntity getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRateEntity taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
