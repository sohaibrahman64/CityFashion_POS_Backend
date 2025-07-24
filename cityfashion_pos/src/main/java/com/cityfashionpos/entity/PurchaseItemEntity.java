package com.cityfashionpos.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "purchase_item")
public class PurchaseItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "purchase_id")
	@JsonBackReference
    private PurchaseEntity purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name="quantity")
    private Integer quantity;
    
    @Column(name="price_per_unit")
    private Double pricePerUnit;
    
    @Column(name="discount_percent")
    private Double discountPercent;
    
    @Column(name="tax_percent")
    private Double taxPercent;
    
    @Column(name="total_price")
    private Double totalPrice;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PurchaseEntity getPurchase() {
		return purchase;
	}
	public void setPurchase(PurchaseEntity purchase) {
		this.purchase = purchase;
	}
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public Double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Double getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
