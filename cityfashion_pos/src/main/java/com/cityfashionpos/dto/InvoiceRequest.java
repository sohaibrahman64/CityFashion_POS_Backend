package com.cityfashionpos.dto;

import java.util.List;

import com.cityfashionpos.entity.CustomerEntity;

public class InvoiceRequest {
	private String invoiceNumber; // 👈 Add this
	private Long customerId;
//	private CustomerEntity customer;
	private Double discountPercent;
	private Double paidAmount;
	private Double taxAmount;
	private Double totalAmount;
	private Double subtotalAmount;
	private Double discountAmount;
	private Double dueAmount;
	private List<PaymentEntry> payments;

	public static class PaymentEntry {
		private int paymentModeId;
		private double amount;

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public int getPaymentModeId() {
			return paymentModeId;
		}

		public void setPaymentModeId(int paymentModeId) {
			this.paymentModeId = paymentModeId;
		}

	}

	private List<InvoiceItemRequest> items;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Double getDiscountPercent() {
		return discountPercent;
	}

//	public CustomerEntity getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(CustomerEntity customer) {
//		this.customer = customer;
//	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public List<InvoiceItemRequest> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItemRequest> items) {
		this.items = items;
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

	public Double getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(Double subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public List<PaymentEntry> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentEntry> payments) {
		this.payments = payments;
	}
	
	
}
