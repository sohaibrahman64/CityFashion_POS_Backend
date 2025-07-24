package com.cityfashionpos.dto;

import com.cityfashionpos.entity.ProductEntity;

public class InvoiceItemRequest {
	private Long productId;
	private Integer quantity;
	private Double price;
	private Double taxPercent;
	private ProductEntity product;
	
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
	public Double getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}
	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	
	
}