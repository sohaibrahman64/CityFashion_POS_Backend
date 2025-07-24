package com.cityfashionpos.dto;

import java.time.LocalDate;
import java.util.List;

public class PurchaseDTO {
	private Long id;
    private Long supplierId;
    private String supplierName;
    private String billNumber;
    private LocalDate purchaseDate;
    private String paymentMode;
    private String description;
    private String imagePath;
    private Double totalAmount;
    private Long paymentModeId;
    
    private List<PurchaseItemDTO> items;
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public List<PurchaseItemDTO> getItems() {
		return items;
	}
	public void setItems(List<PurchaseItemDTO> items) {
		this.items = items;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPaymentModeId() {
		return paymentModeId;
	}
	public void setPaymentModeId(Long paymentModeId) {
		this.paymentModeId = paymentModeId;
	}
	
}

