package com.cityfashionpos.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.NewProformaInvoiceRequest;
import com.cityfashionpos.dto.NewProformaInvoiceResponse;
import com.cityfashionpos.entity.NewProformaInvoiceEntity;
import com.cityfashionpos.entity.NewProformaInvoiceItemEntity;
import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.repository.NewProformaInvoiceItemRepository;
import com.cityfashionpos.repository.NewProformaInvoiceRepository;
import com.cityfashionpos.repository.TaxRateRepository;
import com.cityfashionpos.utils.NumberToWordsConverter;

@Service
public class NewProformaInvoiceService {

    @Autowired
    private NewProformaInvoiceRepository proformaInvoiceRepository;

    @Autowired
    private NewProformaInvoiceItemRepository proformaInvoiceItemRepository;

    @Autowired
    private TaxRateService taxRateService;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Transactional
    public NewProformaInvoiceResponse createNewProformaInvoice(NewProformaInvoiceRequest request) {
        NewProformaInvoiceResponse response = new NewProformaInvoiceResponse();

        try {
            // Generate proforma invoice number
            String proformaInvoiceNumber = generateProformaInvoiceNumber();
            double totalAmount = 0.0;

            // Create proforma invoice entity and set fields
            NewProformaInvoiceEntity proformaInvoiceEntity = new NewProformaInvoiceEntity();
            proformaInvoiceEntity.setProformaInvoiceNumber(proformaInvoiceNumber);
            proformaInvoiceEntity.setPartyId(request.getPartyId());
            proformaInvoiceEntity.setProformaInvoiceDate(LocalDate.now().toString());
            proformaInvoiceEntity.setTotalAmount(request.getTotalAmount());
            proformaInvoiceEntity.setDiscountAmount(request.getDiscountAmount());
            proformaInvoiceEntity.setTotalTaxAmount(request.getTotalTaxAmount());
            proformaInvoiceEntity.setTaxableAmount(request.getTaxableAmount());
            proformaInvoiceEntity
                    .setAmountInWords(NumberToWordsConverter.convertToWords(request.getTotalAmount()));
            proformaInvoiceEntity.setMessage("Proforma Invoice created successfully");
            proformaInvoiceEntity.setSuccess(true);
            proformaInvoiceEntity.setTotalQuantity(request.getTotalQuantity());
            proformaInvoiceEntity.setStatus(request.getStatus());
            proformaInvoiceEntity.setCreatedAt(LocalDateTime.now().toString());
            proformaInvoiceEntity.setUpdatedAt(LocalDateTime.now().toString());

            // Save proforma invoice first to get ID
            proformaInvoiceEntity = proformaInvoiceRepository.save(proformaInvoiceEntity);

            // Process items
            List<NewProformaInvoiceResponse.NewProformaInvoiceItemResponse> responseItems = new ArrayList<>();

            for (NewProformaInvoiceRequest.NewProformaInvoiceItemRequest itemRequest : request.getItems()) {
                if (itemRequest.getItemName() != null && !itemRequest.getItemName().trim().isEmpty()) {
                    // Validate and get tax rate if provided
                    TaxRateEntity taxRate = null;
                    java.math.BigDecimal taxPercent = java.math.BigDecimal.ZERO;
                    if (itemRequest.getTaxRateId() != null) {
                        // Validate tax rate esitst and is active
                        if (!taxRateService.isTaxRateActive(itemRequest.getTaxRateId())) {
                            throw new IllegalArgumentException(
                                    "Invalid or inactive tax rate with id: " + itemRequest.getTaxRateId());
                        }
                        taxRate = taxRateService.getTaxRateById(itemRequest.getTaxRateId());
                        if (taxRate != null) {
                            taxPercent = taxRate.getRate();
                        }
                    }

                    // Calculate item totals
                    double itemSubtotal = (itemRequest.getQuantity() != null ? itemRequest.getQuantity() : 0.0) *
                            (itemRequest.getPrice() != null ? itemRequest.getPrice() : 0.0);

                    double itemDiscountAmount = itemSubtotal
                            * (itemRequest.getDiscount() != null ? itemRequest.getDiscount() : 0) / 100;

                    double itemTotal = itemSubtotal - itemDiscountAmount;

                    // Update running totals
                    totalAmount += itemTotal;

                    // Create estimate quotation item entity
                    NewProformaInvoiceItemEntity proformaInvoiceItem = new NewProformaInvoiceItemEntity();
                    proformaInvoiceItem.setProformaInvoiceId(proformaInvoiceEntity.getId());
                    proformaInvoiceItem.setItemId(itemRequest.getItemId());
                    proformaInvoiceItem.setQuantity(itemRequest.getQuantity());
                    proformaInvoiceItem.setPrice(itemRequest.getPrice());
                    proformaInvoiceItem.setDiscountPercent(itemRequest.getDiscount());
                    proformaInvoiceItem.setDiscountAmount(itemDiscountAmount);
                    proformaInvoiceItem.setTotal(itemTotal);
                    proformaInvoiceItem.setTaxAmount(itemRequest.getTaxAmount());
                    proformaInvoiceItem.setTaxRateIndex(itemRequest.getTaxRateIndex());

                    // Set tax rate information
                    proformaInvoiceItem.setTaxPercent(itemRequest.getTaxPercent());
                    proformaInvoiceItem.setTaxRateId(itemRequest.getTaxRateId());

                    // Save estimate/quotation item
                    proformaInvoiceItemRepository.save(proformaInvoiceItem);

                    // Create response Item
                    NewProformaInvoiceResponse.NewProformaInvoiceItemResponse responseItem = new NewProformaInvoiceResponse.NewProformaInvoiceItemResponse();
                    responseItem.setId(itemRequest.getId());
                    responseItem.setItemId(itemRequest.getItemId());
                    responseItem.setItemName(itemRequest.getItemName());
                    responseItem.setHsnCode("HSN Code");
                    responseItem.setQuantity(itemRequest.getQuantity());
                    responseItem.setPrice(itemRequest.getPrice());
                    responseItem.setDiscount(itemRequest.getDiscount());
                    responseItem.setDiscountAmount(itemRequest.getDiscountAmount());
                    responseItem.setTotal(itemRequest.getTotal());
                    responseItem.setTaxAmount(itemRequest.getTaxAmount());
                    responseItem.setTaxPercent(itemRequest.getTaxPercent());
                    responseItem.setTaxRate(taxRateRepository.findById(itemRequest.getTaxRateId())
                            .get());
                    responseItems.add(responseItem);
                }
            }
            // Save updated estimate quotation
            proformaInvoiceRepository.save(proformaInvoiceEntity);
            response.setProformaInvoiceId(proformaInvoiceEntity.getId());
            response.setProformaInvoiceNumber(proformaInvoiceNumber);
            response.setProformaInvoiceDate(LocalDate.parse(proformaInvoiceEntity.getProformaInvoiceDate()));
            response.setPartyName(request.getPartyName());
            response.setPartyPhone(request.getPartyPhone());
            response.setItems(responseItems);
            response.setTotalDiscountAmount(proformaInvoiceEntity.getDiscountAmount());
            response.setTotalAmount(proformaInvoiceEntity.getTotalAmount());
            response.setTotalTaxAmount(proformaInvoiceEntity.getTotalTaxAmount());
            response.setTaxableAmount(proformaInvoiceEntity.getTotalTaxAmount());
            response.setTaxableAmount(proformaInvoiceEntity.getTaxableAmount());
            response.setAmountInWords(NumberToWordsConverter.convertToWords(totalAmount));
            response.setSuccess(true);
            response.setMessage("Proforma Invoice created successfully");
            response.setTotalQuantity(proformaInvoiceEntity.getTotalQuantity());
            response.setStatus(proformaInvoiceEntity.getStatus());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating proforma invoice: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    private String generateProformaInvoiceNumber() {
        Long latestId = proformaInvoiceRepository.findMaxProformaInvoiceId();
        if (latestId == null) {
            latestId = 0L;
        }
        return String.format("PI-%05d", latestId + 1);
    }

}
