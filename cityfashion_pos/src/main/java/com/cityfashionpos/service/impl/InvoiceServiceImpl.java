package com.cityfashionpos.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.InvoiceItemRequest;
import com.cityfashionpos.dto.InvoiceRequest;
import com.cityfashionpos.entity.CustomerEntity;
import com.cityfashionpos.entity.InventoryEntity;
import com.cityfashionpos.entity.InventoryMovementTypeEntity;
import com.cityfashionpos.entity.InventoryMovementsEntity;
import com.cityfashionpos.entity.InvoiceEntity;
import com.cityfashionpos.entity.InvoiceItemEntity;
import com.cityfashionpos.entity.InvoicePaymentEntity;
import com.cityfashionpos.entity.PaymentTypesEntity;
import com.cityfashionpos.entity.PaymentStatusEntity;
import com.cityfashionpos.entity.ProductEntity;
import com.cityfashionpos.repository.CustomerRepository;
import com.cityfashionpos.repository.InventoryMovementsRepository;
import com.cityfashionpos.repository.InventoryMovementsTypeRepository;
import com.cityfashionpos.repository.InventoryRepository;
import com.cityfashionpos.repository.InvoiceItemRepository;
import com.cityfashionpos.repository.InvoicePaymentRepository;
import com.cityfashionpos.repository.InvoiceRepository;
import com.cityfashionpos.repository.PaymentTypesRepository;
import com.cityfashionpos.repository.PaymentStatusRepository;
import com.cityfashionpos.repository.ProductRepository;
import com.cityfashionpos.response.InvoiceMapper;
import com.cityfashionpos.response.InvoicePrintResponse;
import com.cityfashionpos.response.InvoiceSpecification;
import com.cityfashionpos.response.PastInvoiceResponse;
import com.cityfashionpos.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceItemRepository invoiceItemRepository;

	@Autowired
	private PaymentStatusRepository paymentStatusRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PaymentTypesRepository paymentModeRepository;

	@Autowired
	private InvoicePaymentRepository invoicePaymentRepository;

	@Autowired
	private InvoiceMapper invoiceMapper;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private InventoryMovementsRepository inventoryMovementsRepository;

	@Autowired
	private InventoryMovementsTypeRepository inventoryMovementsTypeRepository;

	@Override
	public InvoicePrintResponse createInvoice(InvoiceRequest request) {
		InvoiceEntity invoice = new InvoiceEntity();
		PaymentStatusEntity paymentStatusEntity;
		Double discountAmount = request.getDiscountAmount();
		Double totalAmount = calculateTotal(request.getItems()) - discountAmount;
		Double paidAmount = request.getPayments().stream().mapToDouble(InvoiceRequest.PaymentEntry::getAmount).sum();
		invoice.setInvoiceNumber(request.getInvoiceNumber());
		invoice.setCustomerId(request.getCustomerId());
		// invoice.setCustomer(request.getCustomer());
		invoice.setDiscountPercent(request.getDiscountPercent());
		invoice.setPaidAmount(paidAmount);
		invoice.setTaxAmount(request.getTaxAmount());
		invoice.setSubtotalAmount(request.getSubtotalAmount());
		invoice.setTotalAmount(totalAmount);
		invoice.setDiscountAmount(discountAmount);
		invoice.setDueAmount(request.getDueAmount());
		if (Math.floor(paidAmount) >= Math.floor(totalAmount)) {
			paymentStatusEntity = paymentStatusRepository.findByStatusName("Paid");
		} else if (paidAmount > 0) {
			paymentStatusEntity = paymentStatusRepository.findByStatusName("Partial");
		} else {
			paymentStatusEntity = paymentStatusRepository.findByStatusName("Due");
		}
		invoice.setPaymentStatus(paymentStatusEntity);

		InvoiceEntity savedInvoice = invoiceRepository.save(invoice);

		List<InvoicePaymentEntity> payments = setPaymentEntries(request, savedInvoice);

		List<InvoiceItemEntity> invoiceItems = new ArrayList<>();

		for (InvoiceItemRequest item : request.getItems()) {
			InventoryEntity inventory = inventoryRepository.findByProductId(item.getProductId()).orElseThrow(
					() -> new RuntimeException("Inventory not found for productId: " + item.getProductId()));

			if (inventory.getQuantity() < item.getQuantity()) {
				throw new RuntimeException("Insufficient stock for productId: " + item.getProductId());
			}

			// Reduce stock
			inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
			inventoryRepository.save(inventory);

			InvoiceItemEntity invoiceItem = new InvoiceItemEntity();
			invoiceItem.setInvoiceId(savedInvoice.getId());
			invoiceItem.setProductId(item.getProductId());
			invoiceItem.setQuantity(item.getQuantity());
			invoiceItem.setPrice(item.getPrice());
			invoiceItem.setTaxPercent(item.getTaxPercent());
			Double total = calculateTotalOfInvoiceItem(item.getPrice(), item.getQuantity(), item.getTaxPercent());
			invoiceItem.setTotal(total);

			invoiceItems.add(invoiceItem);
			invoiceItemRepository.save(invoiceItem);

			ProductEntity product = productRepository.findById(invoiceItem.getProductId()).orElseThrow(
					() -> new EntityNotFoundException("Product not found with ID: " + invoiceItem.getProductId()));

			// Log inventory movement
			createInventoryMovement(product, invoice.getInvoiceNumber(), invoiceItem.getQuantity(), "SALE");
		}

		InvoicePrintResponse invoicePrintResponse = getInvoicePrintResponse(invoice, request, invoiceItems, payments);
		return invoicePrintResponse;
	}

	private Double calculateTotalOfInvoiceItem(Double price, Integer quantity, Double taxPercent) {
		Double base = price * quantity;
		Double taxAmount = base * (taxPercent / 100.0);
		return (base + taxAmount);
	}

	private InvoicePrintResponse getInvoicePrintResponse(InvoiceEntity invoice, InvoiceRequest request,
			List<InvoiceItemEntity> items, List<InvoicePaymentEntity> payments) {
		InvoicePrintResponse response = new InvoicePrintResponse();
		response.setStatus("success");
		response.setInvoiceId(invoice.getId());
		response.setInvoiceNumber(invoice.getInvoiceNumber());
		response.setInvoiceDate(invoice.getInvoiceDate());

		InvoicePrintResponse.PrintData printData = new InvoicePrintResponse.PrintData();

		// Customer
		Optional<CustomerEntity> customer = Optional
				.ofNullable(customerRepository.findById(request.getCustomerId()).orElse(null));
		// .ofNullable(customerRepository.findByCustomer(request.getCustomer()).orElse(null));
		InvoicePrintResponse.CustomerInfo customerInfo = new InvoicePrintResponse.CustomerInfo();
		customerInfo.setName(customer != null ? customer.get().getName() : "Walk - In");
		customerInfo.setPhone(customer != null ? customer.get().getPhone() : "");
		customerInfo.setAddress(customer != null ? customer.get().getAddress() : "NA");
		customerInfo.setEmail(customer != null ? customer.get().getEmail() : "NA");
		printData.setCustomer(customerInfo);

		List<InvoicePrintResponse.InvoiceItemInfo> itemInfos = items.stream().map(i -> {
			InvoicePrintResponse.InvoiceItemInfo info = new InvoicePrintResponse.InvoiceItemInfo();
			info.setProductName(
					// productRepository.findByProduct(i.getProduct()).map(ProductEntity::getName).orElse("Unknown"));
					productRepository.findById(i.getProductId()).map(ProductEntity::getName).orElse("Unknown"));
			info.setQuantity(i.getQuantity());
			info.setPrice(i.getPrice());
			info.setTotal(i.getTotal());
			return info;
		}).collect(Collectors.toList());
		printData.setItems(itemInfos);

		// Totals
		InvoicePrintResponse.TotalsInfo totals = new InvoicePrintResponse.TotalsInfo();
		totals.setSubtotal(invoice.getSubtotalAmount());
		totals.setTax(invoice.getTaxAmount());
		totals.setDiscount(invoice.getDiscountAmount());
		totals.setGrandTotal(invoice.getTotalAmount());
		totals.setPaid(invoice.getPaidAmount());
		totals.setDue(invoice.getDueAmount());
		printData.setTotals(totals);

		// Payments
		// InvoicePrintResponse.PaymentInfo payment = new
		// InvoicePrintResponse.PaymentInfo();
		// payment.setPaymentMode(null);
		// payment.setAmount(invoice.getPaidAmount());

		List<InvoicePrintResponse.PaymentInfo> paymentInfos = payments.stream().map(item -> {
			InvoicePrintResponse.PaymentInfo info = new InvoicePrintResponse.PaymentInfo();
			info.setPaymentMode(item.getPaymentMode().getPaymentType());
			info.setAmount(item.getAmount());
			return info;
		}).collect(Collectors.toList());
		printData.setPayments(paymentInfos);

		response.setPrintData(printData);
		return response;
	}

	private Double calculateTotal(List<InvoiceItemRequest> items) {
		double total = 0.0;

		for (InvoiceItemRequest item : items) {
			double itemTotal = item.getPrice() * item.getQuantity();
			if (item.getTaxPercent() != null && item.getTaxPercent() > 0) {
				double taxAmount = itemTotal * (item.getTaxPercent() / 100);
				itemTotal += taxAmount;
			}
			total += itemTotal;
		}

		return total;
	}

	private List<InvoicePaymentEntity> setPaymentEntries(InvoiceRequest request, InvoiceEntity savedInvoice) {
		List<InvoicePaymentEntity> payments = new ArrayList<>();
		for (InvoiceRequest.PaymentEntry paymentEntry : request.getPayments()) {
			PaymentTypesEntity mode = paymentModeRepository.findById((long) paymentEntry.getPaymentModeId())
					.orElseThrow(() -> new RuntimeException("Invalid payment mode"));

			InvoicePaymentEntity payment = new InvoicePaymentEntity();
			payment.setInvoice(savedInvoice);
			payment.setPaymentMode(mode);
			payment.setAmount(paymentEntry.getAmount());
			payments.add(payment);
			invoicePaymentRepository.save(payment);
		}
		return payments;
	}

	@Override
	public List<PastInvoiceResponse> getFilteredInvoices(Long customerId, String invoiceNumber, String fromDate,
			String toDate, Integer paymentModeId, String paymentStatus) {
		Specification<InvoiceEntity> spec = InvoiceSpecification.build(customerId, invoiceNumber, fromDate, toDate,
				paymentModeId, paymentStatus);

		List<InvoiceEntity> invoices = invoiceRepository.findAll(spec);

		return invoices.stream().map(invoiceMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public InvoicePrintResponse getInvoicePrintDetails(Long invoiceId) {
		InvoiceEntity invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new RuntimeException("Invoice not found"));

		InvoicePaymentEntity invoicePayment = invoicePaymentRepository.findById(invoiceId)
				.orElseThrow(() -> new RuntimeException("Invoice not found"));

		InvoicePrintResponse response = new InvoicePrintResponse();
		response.setStatus("success");
		response.setInvoiceId(invoice.getId());
		response.setInvoiceNumber(invoice.getInvoiceNumber());
		response.setInvoiceDate(invoice.getInvoiceDate());

		InvoicePrintResponse.PrintData printData = new InvoicePrintResponse.PrintData();

		CustomerEntity customerEntity = customerRepository.findById(invoice.getCustomerId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		InvoicePrintResponse.CustomerInfo customerInfo = new InvoicePrintResponse.CustomerInfo();
		customerInfo.setName(customerEntity.getName());
		customerInfo.setPhone(customerEntity.getPhone());
		customerInfo.setEmail(customerEntity.getEmail());
		customerInfo.setAddress(customerEntity.getAddress());
		printData.setCustomer(customerInfo);

		List<InvoicePrintResponse.InvoiceItemInfo> itemInfos = invoice.getItems().stream().map(item -> {
			InvoicePrintResponse.InvoiceItemInfo info = new InvoicePrintResponse.InvoiceItemInfo();
			info.setProductName(
					productRepository.findById(item.getProductId()).map(ProductEntity::getName).orElse("Unknown"));
			info.setQuantity(item.getQuantity());
			info.setPrice(item.getPrice());
			info.setTotal(item.getTotal());
			return info;
		}).collect(Collectors.toList());
		printData.setItems(itemInfos);

		List<InvoicePrintResponse.PaymentInfo> paymentInfos = invoice.getPayments().stream().map(item -> {
			InvoicePrintResponse.PaymentInfo info = new InvoicePrintResponse.PaymentInfo();
			info.setPaymentMode(item.getPaymentMode().getPaymentType());
			info.setAmount(item.getAmount());
			return info;
		}).collect(Collectors.toList());
		printData.setPayments(paymentInfos);

		InvoicePrintResponse.TotalsInfo totals = new InvoicePrintResponse.TotalsInfo();
		totals.setSubtotal(invoice.getSubtotalAmount());
		totals.setTax(invoice.getTaxAmount());
		totals.setDiscount(invoice.getDiscountAmount());
		totals.setGrandTotal(invoice.getTotalAmount());
		totals.setPaid(invoice.getPaidAmount());
		totals.setDue(invoice.getDueAmount());
		printData.setTotals(totals);

		// Payments
		// InvoicePrintResponse.PaymentInfo payment = new
		// InvoicePrintResponse.PaymentInfo();
		// payment.setAmount(invoice.getPaidAmount());

		response.setPrintData(printData);

		return response;
	}

	private void createInventoryMovement(ProductEntity product, String invoiceNumber, int quantity,
			String inventoryMovementType) {
		InventoryMovementTypeEntity inventoryMovementTypeEntity = inventoryMovementsTypeRepository
				.findByMovementType(inventoryMovementType)
				.orElseThrow(() -> new IllegalStateException("Movement type not found: " + inventoryMovementType));

		InventoryMovementsEntity inventoryMovementEntity = new InventoryMovementsEntity();
		inventoryMovementEntity.setProduct(product);
		inventoryMovementEntity.setQuantityChange(quantity);
		inventoryMovementEntity.setInventoryMovementType(inventoryMovementTypeEntity);
		inventoryMovementEntity.setReferenceId("Invoice#" + invoiceNumber);
		inventoryMovementEntity.setMovementDate(LocalDate.now());

		inventoryMovementsRepository.save(inventoryMovementEntity);
	}

}
