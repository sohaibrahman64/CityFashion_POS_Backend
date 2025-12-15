package com.cityfashionpos.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cityfashionpos.entity.CustomerEntity;
import com.cityfashionpos.entity.InvoiceEntity;
import com.cityfashionpos.entity.InvoiceItemEntity;
import com.cityfashionpos.entity.InvoicePaymentEntity;
import com.cityfashionpos.repository.CustomerRepository;

@Component
public class InvoiceMapper {

    @Autowired
    CustomerRepository customerRepository;

    public PastInvoiceResponse toDto(InvoiceEntity invoice) {
        PastInvoiceResponse dto = new PastInvoiceResponse();

        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setSubtotalAmount(invoice.getSubtotalAmount());
        dto.setDiscountPercent(invoice.getDiscountPercent());
        dto.setDiscountAmount(invoice.getDiscountAmount());
        dto.setTaxAmount(invoice.getTaxAmount());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setPaidAmount(invoice.getPaidAmount());
        dto.setDueAmount(invoice.getDueAmount());
        dto.setPaymentStatus(invoice.getPaymentStatus().getStatusName());

        CustomerEntity customer = customerRepository.findById(invoice.getCustomerId()).orElse(null);
        if (customer != null) {
            dto.setCustomerName(customer.getName());
            dto.setCustomerPhone(customer.getPhone());
            dto.setCustomerEmail(customer.getEmail());
            dto.setCustomerAddress(customer.getAddress());
        }

        // Set Invoice Items
        List<InvoiceItemResponse> itemResponses = invoice.getItems().stream()
                .map(InvoiceMapper::mapItem)
                .collect(Collectors.toList());
        dto.setItems(itemResponses);

        // Set Payment Breakdown
        List<PaymentBreakupResponse> paymentResponses = invoice.getPayments().stream()
                .map(InvoiceMapper::mapPayment)
                .collect(Collectors.toList());
        dto.setPayments(paymentResponses);

        return dto;
    }

    private static InvoiceItemResponse mapItem(InvoiceItemEntity item) {
        InvoiceItemResponse dto = new InvoiceItemResponse();
        // dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        dto.setTaxPercent(item.getTaxPercent());

        double base = item.getPrice() * item.getQuantity();
        double tax = base * (item.getTaxPercent() / 100.0);
        dto.setTotal(base + tax);

        return dto;
    }

    private static PaymentBreakupResponse mapPayment(InvoicePaymentEntity payment) {
        PaymentBreakupResponse dto = new PaymentBreakupResponse();
        dto.setPaymentMode(payment.getPaymentMode().getPaymentMode());
        dto.setAmount(payment.getAmount());
        return dto;
    }
}
