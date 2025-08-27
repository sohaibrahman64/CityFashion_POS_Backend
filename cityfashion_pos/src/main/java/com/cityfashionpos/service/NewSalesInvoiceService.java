package com.cityfashionpos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityfashionpos.dto.NewSalesInvoiceRequest;
import com.cityfashionpos.dto.NewSalesInvoiceResponse;
import com.cityfashionpos.entity.CustomerEntity;
import com.cityfashionpos.entity.NewSalesInvoiceEntity;
import com.cityfashionpos.entity.NewSalesInvoiceItemEntity;
import com.cityfashionpos.repository.CustomerRepository;
import com.cityfashionpos.repository.NewSalesInvoiceItemRepository;
import com.cityfashionpos.repository.NewSalesInvoiceRepository;
import com.cityfashionpos.utils.NumberToWordsConverter;

@Service
public class NewSalesInvoiceService {

    @Autowired
    private NewSalesInvoiceRepository invoiceRepository;

    @Autowired
    private NewSalesInvoiceItemRepository invoiceItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public NewSalesInvoiceResponse createNewSalesInvoice(NewSalesInvoiceRequest request) {
        NewSalesInvoiceResponse response = new NewSalesInvoiceResponse();

        try {
            // Generate invoice number
            String invoiceNumber = generateInvoiceNumber();

            // Create or find customer
            CustomerEntity customer = createOrFindCustomer(request.getCustomerName(), request.getCustomerPhone());

            // Calculate totals
            double subtotalAmount = 0.0;
            double totalDiscountAmount = 0.0;
            double totalAmount = 0.0;

            // Create invoice entity
            NewSalesInvoiceEntity invoice = new NewSalesInvoiceEntity();
            invoice.setInvoiceNumber(invoiceNumber);
            invoice.setCustomerId(customer.getId());
            invoice.setInvoiceDate(LocalDate.now());
            invoice.setTotalAmount(request.getTotalAmount());
            invoice.setReceivedAmount(request.getReceivedAmount() != null ? request.getReceivedAmount() : 0.0);
            invoice.setBalanceAmount(request.getBalanceAmount());
            invoice.setDiscountAmount(request.getDiscountAmount());
            invoice.setAmountInWords(NumberToWordsConverter.convertToWords(request.getTotalAmount()));
            invoice.setMessage("Sales invoice created successfully");
            invoice.setSuccess(true);

            // Save invoice first to get ID
            invoice = invoiceRepository.save(invoice);

            // Process items
            List<NewSalesInvoiceResponse.NewSalesInvoiceItemResponse> responseItems = new ArrayList<>();

            for (NewSalesInvoiceRequest.NewSalesInvoiceItemRequest itemRequest : request.getItems()) {
                if (itemRequest.getItemName() != null && !itemRequest.getItemName().trim().isEmpty()) {
                    // Calculate item totals
                    double itemSubtotal = (itemRequest.getQuantity() != null ? itemRequest.getQuantity() : 0) *
                            (itemRequest.getPrice() != null ? itemRequest.getPrice() : 0);
                    double itemDiscountAmount = itemSubtotal
                            * (itemRequest.getDiscount() != null ? itemRequest.getDiscount() : 0) / 100;
                    double itemTotal = itemSubtotal - itemDiscountAmount;

                    // Update running totals
                    subtotalAmount += itemSubtotal;
                    totalDiscountAmount += itemDiscountAmount;
                    totalAmount += itemTotal;

                    // Create invoice item entity
                    NewSalesInvoiceItemEntity invoiceItem = new NewSalesInvoiceItemEntity();
                    invoiceItem.setInvoiceId(invoice.getId());
                    invoiceItem.setProductId(itemRequest.getProductId());
                    invoiceItem.setQuantity(itemRequest.getQuantity());
                    invoiceItem.setPrice(itemRequest.getPrice());
                    invoiceItem.setDiscountPercent(itemRequest.getDiscount());
                    invoiceItem.setDiscountAmount(itemDiscountAmount);
                    invoiceItem.setTotal(itemTotal);

                    // Save invoice item
                    invoiceItemRepository.save(invoiceItem);

                    // Create response item
                    NewSalesInvoiceResponse.NewSalesInvoiceItemResponse responseItem = new NewSalesInvoiceResponse.NewSalesInvoiceItemResponse();
                    responseItem.setId(itemRequest.getId());
                    responseItem.setItemName(itemRequest.getItemName());
                    responseItem.setHsnCode("HSN Code"); // Default value, can be enhanced later
                    responseItem.setQuantity(itemRequest.getQuantity());
                    responseItem.setPrice(itemRequest.getPrice());
                    responseItem.setDiscount(itemRequest.getDiscount());
                    responseItem.setDiscountAmount(itemDiscountAmount);
                    responseItem.setTotal(itemTotal);

                    responseItems.add(responseItem);
                }
            }

            // Update invoice with calculated totals
            invoice.setTotalAmount(totalAmount);

            double receivedAmount = request.getReceivedAmount() != null ? request.getReceivedAmount() : 0.0;
            double balanceAmount = totalAmount - receivedAmount;
            invoice.setBalanceAmount(balanceAmount);

            // Save updated invoice
            invoiceRepository.save(invoice);

            // Prepare response
            response.setInvoiceId(invoice.getId());
            response.setInvoiceNumber(invoiceNumber);
            response.setInvoiceDate(invoice.getInvoiceDate());
            response.setCustomerName(customer.getName());
            response.setCustomerPhone(customer.getPhone());
            response.setItems(responseItems);
            response.setSubtotalAmount(subtotalAmount);
            response.setTotalDiscountAmount(totalDiscountAmount);
            response.setTotalAmount(totalAmount);
            response.setReceivedAmount(receivedAmount);
            response.setBalanceAmount(balanceAmount);
            response.setDiscountAmount(totalDiscountAmount);
            response.setAmountInWords(NumberToWordsConverter.convertToWords(totalAmount));
            response.setSuccess(true);
            response.setMessage("Sales invoice created successfully");

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating sales invoice: " + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    private String generateInvoiceNumber() {
        Long latestId = invoiceRepository.findMaxInvoiceId();
        if (latestId == null) {
            latestId = 0L;
        }
        return String.format("RS-%05d", latestId + 1);
    }

    private CustomerEntity createOrFindCustomer(String name, String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            // Try to find existing customer by phone - since findByPhone doesn't exist,
            // we'll search by name
            List<CustomerEntity> existingCustomers = customerRepository.findByNameContainingIgnoreCase(phone);
            if (!existingCustomers.isEmpty()) {
                // Find exact phone match
                for (CustomerEntity customer : existingCustomers) {
                    if (phone.equals(customer.getPhone())) {
                        // Update name if it has changed
                        if (name != null && !name.trim().isEmpty() && !name.equals(customer.getName())) {
                            customer.setName(name);
                            customerRepository.save(customer);
                        }
                        return customer;
                    }
                }
            }
        }

        // Create new customer
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(name != null ? name : "Customer");
        newCustomer.setPhone(phone);
        return customerRepository.save(newCustomer);
    }

}
