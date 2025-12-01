package com.cityfashionpos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "new_estimate_quotation_items")
public class NewEstimateQuotationItemEntity {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estimate_quotation_id")
    private Long estimateQuotationId; // Reference to EstimateQuotation

    @Column(name = "item_id")
    private Long itemId; // Reference to Items

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price; // Unit price at the time of estimate quotation

    @Column(name = "total_price")
    private Double total; // price * quantity

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "tax_percent")
    private Double taxPercent; // snapshot of applied rate

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "tax_rate_id")
    private Long taxRateId; // Reference to TaxRateEntity

    @Column(name = "tax_rate_index")
    private Long taxRateIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstimateQuotationId() {
        return estimateQuotationId;
    }

    public void setEstimateQuotationId(Long estimateQuotationId) {
        this.estimateQuotationId = estimateQuotationId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
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
