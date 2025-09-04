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

    @Column(name = "product_id")
    private Long productId; // Reference to Product

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price; // Unit price at the time of sale

    @Column(name = "total_price")
    private Double total; // price * quantity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_rate_id")
    private TaxRateEntity taxRate;

    @Column(name = "tax_percent", precision = 5, scale = 2)
    private BigDecimal taxPercent; // snapshot of applied rate

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "discount_amount")
    private Double discountAmount;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public TaxRateEntity getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRateEntity taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(BigDecimal taxPercent) {
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

}
