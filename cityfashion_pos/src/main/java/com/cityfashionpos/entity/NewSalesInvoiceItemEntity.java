package com.cityfashionpos.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "new_sales_invoice_items")
public class NewSalesInvoiceItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_id")
    private Long invoiceId; // Reference to Invoice

    @Column(name = "item_id")
    private Long itemId; // Reference to Items

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price; // Unit price at the time of sale

    @Column(name = "total_price")
    private Double total; // price * quantity

    @Column(name = "tax_percent", precision = 5, scale = 2)
    private Double taxPercent; // snapshot of applied rate

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "tax_amount")
    private Double taxAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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

}
