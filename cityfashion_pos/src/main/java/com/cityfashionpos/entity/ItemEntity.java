package com.cityfashionpos.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hsn")
    private String hsn;

    @Column(name = "unit")
    private String unit;

    @Column(name = "category")
    private String category;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "image_path")
    private String imagePath;

    // Pricing details
    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal salePrice = BigDecimal.ZERO;

    @Column(name = "sale_price_type")
    private String salePriceType;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "discount_type")
    private String discountType;

    // Purchase and tax details
    @Column(name = "purchase_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal purchasePrice = BigDecimal.ZERO;

    @Column(name = "purchase_price_type")
    private String purchasePriceType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tax_rate_id")
    private TaxRateEntity taxRate;

    // Stock details
    @Column(name = "opening_quantity")
    private Integer openingQuantity = 0;

    @Column(name = "at_price", precision = 10, scale = 2)
    private BigDecimal atPrice = BigDecimal.ZERO;

    @Column(name = "as_of_date")
    private LocalDate asOfDate;

    @Column(name = "min_stock")
    private Integer minStock = 0;

    @Column(name = "location")
    private String location;

    // Audit fields
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "tax_rate_index")
    private Long taxRateIndex;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getTaxRateIndex() {
        return taxRateIndex;
    }

    public void setTaxRateIndex(Long taxRateIndex) {
        this.taxRateIndex = taxRateIndex;
    }
}