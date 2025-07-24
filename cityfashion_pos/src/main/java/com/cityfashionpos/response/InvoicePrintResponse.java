package com.cityfashionpos.response;

import java.time.LocalDate;
import java.util.List;

import com.cityfashionpos.entity.InvoicePaymentEntity;

public class InvoicePrintResponse {
	private String status;
	private Long invoiceId;
	private String invoiceNumber;
	private PrintData printData;
	// private LocalDateTime invoiceDate;
	private LocalDate invoiceDate;

	public static class PrintData {
		private CustomerInfo customer;
		private List<InvoiceItemInfo> items;
		private TotalsInfo totals;
		private List<PaymentInfo> payments;

		public CustomerInfo getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerInfo customer) {
			this.customer = customer;
		}

		public List<InvoiceItemInfo> getItems() {
			return items;
		}

		public void setItems(List<InvoiceItemInfo> items) {
			this.items = items;
		}

		public TotalsInfo getTotals() {
			return totals;
		}

		public void setTotals(TotalsInfo totals) {
			this.totals = totals;
		}

		public List<PaymentInfo> getPayments() {
			return payments;
		}

		public void setPayments(List<PaymentInfo> payments) {
			this.payments = payments;
		}

	}

	public static class CustomerInfo {
		private String name;
		private String phone;
		private String address;
		private String email;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}

	public static class InvoiceItemInfo {
		private String productName;
		private int quantity;
		private double price;
		private double total;

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

	}

	public static class TotalsInfo {
		private double subtotal;
		private double tax;
		private double discount;
		private double grandTotal;
		private double paid;
		private double due;

		public double getSubtotal() {
			return subtotal;
		}

		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}

		public double getTax() {
			return tax;
		}

		public void setTax(double tax) {
			this.tax = tax;
		}

		public double getDiscount() {
			return discount;
		}

		public void setDiscount(double discount) {
			this.discount = discount;
		}

		public double getGrandTotal() {
			return grandTotal;
		}

		public void setGrandTotal(double grandTotal) {
			this.grandTotal = grandTotal;
		}

		public double getPaid() {
			return paid;
		}

		public void setPaid(double paid) {
			this.paid = paid;
		}

		public double getDue() {
			return due;
		}

		public void setDue(double due) {
			this.due = due;
		}
	}

	public static class PaymentInfo {
		private String paymentMode;
		private Double amount;


		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public String getPaymentMode() {
			return paymentMode;
		}

		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public PrintData getPrintData() {
		return printData;
	}

	public void setPrintData(PrintData printData) {
		this.printData = printData;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

}
