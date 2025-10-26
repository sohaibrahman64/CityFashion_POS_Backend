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
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.repository.CustomerRepository;
import com.cityfashionpos.repository.NewSalesInvoiceItemRepository;
import com.cityfashionpos.repository.NewSalesInvoiceRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.utils.NumberToWordsConverter;

@Service
public class NewSalesInvoiceService {

    @Autowired
    private NewSalesInvoiceRepository invoiceRepository;

    @Autowired
    private NewSalesInvoiceItemRepository invoiceItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private TaxRateService taxRateService;

    @Transactional
    public NewSalesInvoiceResponse createNewSalesInvoice(NewSalesInvoiceRequest request) {
        NewSalesInvoiceResponse response = new NewSalesInvoiceResponse();

        try {
            // Generate invoice number
            String invoiceNumber = generateInvoiceNumber();

            // Create or find customer
            // CustomerEntity customer = createOrFindCustomer(request.getPartyName(),
            // request.getPartyPhone());

            // PartyEntity party = createOrFindParty(request.getPartyName(),
            // request.getPartyPhone());

            // Calculate totals
            double subtotalAmount = 0.0;
            double totalDiscountAmount = 0.0;
            double totalAmount = 0.0;

            // Create invoice entity
            NewSalesInvoiceEntity invoice = new NewSalesInvoiceEntity();
            invoice.setInvoiceNumber(invoiceNumber);
            invoice.setPartyId(request.getPartyId());
            invoice.setInvoiceDate(LocalDate.now());
            invoice.setTotalAmount(request.getTotalAmount());
            invoice.setSubtotalAmount(request.getSubtotalAmount());
            invoice.setReceivedAmount(request.getReceivedAmount() != null ? request.getReceivedAmount() : 0.0);
            invoice.setBalanceAmount(request.getBalanceAmount());
            invoice.setDiscountAmount(request.getDiscountAmount());
            invoice.setTotalTaxAmount(request.getTotalTaxAmount());
            invoice.setTaxableAmount(request.getTaxableAmount());
            invoice.setAmountInWords(NumberToWordsConverter.convertToWords(request.getTotalAmount()));
            invoice.setMessage("Sales invoice created successfully");
            invoice.setSuccess(true);

            // Save invoice first to get ID
            invoice = invoiceRepository.save(invoice);

            // Process items
            List<NewSalesInvoiceResponse.NewSalesInvoiceItemResponse> responseItems = new ArrayList<>();

            for (NewSalesInvoiceRequest.NewSalesInvoiceItemRequest itemRequest : request.getItems()) {
                if (itemRequest.getItemName() != null && !itemRequest.getItemName().trim().isEmpty()) {
                    // Validate and get tax rate if provided
                    TaxRateEntity taxRate = null;
                    java.math.BigDecimal taxPercent = java.math.BigDecimal.ZERO;

                    if (itemRequest.getTaxRateId() != null) {
                        // Validate that tax rate exists and is active
                        if (!taxRateService.isTaxRateActive(itemRequest.getTaxRateId())) {
                            throw new IllegalArgumentException(
                                    "Invalid or inactive tax rate ID: " + itemRequest.getTaxRateId());
                        }
                        taxRate = taxRateService.getTaxRateById(itemRequest.getTaxRateId());
                        if (taxRate != null) {
                            taxPercent = taxRate.getRate();
                        }
                    }

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
                    invoiceItem.setItemId(itemRequest.getItemId());
                    invoiceItem.setQuantity(itemRequest.getQuantity());
                    invoiceItem.setPrice(itemRequest.getPrice());
                    invoiceItem.setDiscountPercent(itemRequest.getDiscount());
                    invoiceItem.setDiscountAmount(itemRequest.getDiscountAmount());
                    invoiceItem.setTotal(itemRequest.getTotal());
                    invoiceItem.setTaxAmount(itemRequest.getTaxAmount());

                    // Set tax rate information
                    // invoiceItem.setTaxRate(itemRequest.getTaxRate());
                    invoiceItem.setTaxPercent(itemRequest.getTaxPercent());

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
                    responseItem.setDiscountAmount(itemRequest.getDiscountAmount());
                    responseItem.setTotal(itemRequest.getTotal());
                    responseItem.setTaxAmount(itemRequest.getTaxAmount());
                    responseItem.setTaxPercent(itemRequest.getTaxPercent());
                    responseItem.setTaxRate(itemRequest.getTaxRate());

                    responseItems.add(responseItem);
                }
            }

            // Update invoice with calculated totals
            // invoice.setTotalAmount(totalAmount);

            // double receivedAmount = request.getReceivedAmount() != null ?
            // request.getReceivedAmount() : 0.0;
            // double balanceAmount = totalAmount - receivedAmount;
            // invoice.setBalanceAmount(balanceAmount);

            // Save updated invoice
            invoiceRepository.save(invoice);

            // Prepare response
            response.setInvoiceId(invoice.getId());
            response.setInvoiceNumber(invoiceNumber);
            response.setInvoiceDate(invoice.getInvoiceDate());
            response.setPartyName(request.getPartyName());
            response.setPartyPhone(request.getPartyPhone());
            response.setItems(responseItems);
            response.setSubtotalAmount(invoice.getSubtotalAmount());
            response.setTotalDiscountAmount(invoice.getDiscountAmount());
            response.setTotalAmount(invoice.getTotalAmount());
            response.setReceivedAmount(invoice.getReceivedAmount());
            response.setBalanceAmount(invoice.getBalanceAmount());
            response.setDiscountAmount(invoice.getDiscountAmount());
            response.setTotalTaxAmount(invoice.getTotalTaxAmount());
            response.setTaxableAmount(invoice.getTaxableAmount());
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

    private PartyEntity createOrFindParty(String partyName, String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            // Try to find existing customer by phone - since findByPhone doesn't exist,
            // we'll search by name
            List<PartyEntity> existingParties = partyRepository.findByPartyNameContainingIgnoreCase(phoneNumber);
            if (!existingParties.isEmpty()) {
                // Find exact phone match
                for (PartyEntity party : existingParties) {
                    if (phoneNumber.equals(party.getPhoneNumber())) {
                        // Update name if it has changed
                        if (partyName != null && !partyName.trim().isEmpty()
                                && !partyName.equals(party.getPartyName())) {
                            party.setPartyName(partyName);
                            party.setPhoneNumber(phoneNumber);
                            partyRepository.save(party);
                        }
                        return party;
                    }
                }
            }
        }

        // Create new customer
        PartyEntity newParty = new PartyEntity();
        newParty.setPartyName(partyName != null ? partyName : "Party");
        newParty.setPhoneNumber(phoneNumber);
        return partyRepository.save(newParty);
    }

}
