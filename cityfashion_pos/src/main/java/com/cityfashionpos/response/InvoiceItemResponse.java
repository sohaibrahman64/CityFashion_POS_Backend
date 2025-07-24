package com.cityfashionpos.response;

public class InvoiceItemResponse {
    private String productName;
    private Integer quantity;
    private Double price;
    private Double total;
    private Double taxPercent;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
    
    
}
