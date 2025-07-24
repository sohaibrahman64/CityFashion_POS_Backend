package com.cityfashionpos.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PastInvoiceResponse {
	
	private Long id;
	private String invoiceNumber;
    private LocalDate invoiceDate;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;

    private Double subtotalAmount;
    private Double discountPercent;
    private Double discountAmount;
    private Double taxAmount;
    private Double totalAmount;
    private Double paidAmount;
    private Double dueAmount;
    private String paymentStatus;
    
    private List<InvoiceItemResponse> items;
    private List<PaymentBreakupResponse> payments;
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Double getSubtotalAmount() {
		return subtotalAmount;
	}
	public void setSubtotalAmount(Double subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
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
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Double getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public List<InvoiceItemResponse> getItems() {
		return items;
	}
	public void setItems(List<InvoiceItemResponse> items) {
		this.items = items;
	}
	public List<PaymentBreakupResponse> getPayments() {
		return payments;
	}
	public void setPayments(List<PaymentBreakupResponse> payments) {
		this.payments = payments;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
