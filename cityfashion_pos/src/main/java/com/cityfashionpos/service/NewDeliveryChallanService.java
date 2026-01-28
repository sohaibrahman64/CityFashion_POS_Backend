package com.cityfashionpos.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.NewDeliveryChallanRequest;
import com.cityfashionpos.dto.NewDeliveryChallanResponse;
import com.cityfashionpos.entity.NewDeliveryChallanEntity;
import com.cityfashionpos.entity.NewDeliveryChallanItemEntity;
import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.repository.NewDeliveryChallanItemRepository;
import com.cityfashionpos.repository.NewDeliveryChallanRepository;
import com.cityfashionpos.repository.TaxRateRepository;
import com.cityfashionpos.utils.NumberToWordsConverter;

@Service
public class NewDeliveryChallanService {
    @Autowired
    private NewDeliveryChallanRepository deliveryChallanRepository;

    @Autowired
    private NewDeliveryChallanItemRepository deliveryChallanItemRepository;

    @Autowired
    private TaxRateService taxRateService;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Transactional
    public NewDeliveryChallanResponse createNewDeliveryChallan(NewDeliveryChallanRequest request) {
        NewDeliveryChallanResponse response = new NewDeliveryChallanResponse();

        try {
            // Generate delivery challan number
            String deliveryChallanNumber = generateDeliveryChallanNumber();
            double totalAmount = 0.0;

            // Create delivery challan entity and set fields
            NewDeliveryChallanEntity deliveryChallanEntity = new NewDeliveryChallanEntity();
            deliveryChallanEntity.setDeliveryChallanNumber(deliveryChallanNumber);
            deliveryChallanEntity.setPartyId(request.getPartyId());
            deliveryChallanEntity.setDeliveryChallanInvoiceDate(LocalDate.now().toString());
            deliveryChallanEntity.setDeliveryChallanDueDate(request.getDeliveryChallanDueDate().toString());
            deliveryChallanEntity.setBillingAddress(request.getBillingAddress());
            deliveryChallanEntity.setTotalAmount(request.getTotalAmount());
            deliveryChallanEntity.setDiscountAmount(request.getDiscountAmount());
            deliveryChallanEntity.setTotalTaxAmount(request.getTotalTaxAmount());
            deliveryChallanEntity.setTaxableAmount(request.getTaxableAmount());
            deliveryChallanEntity
                    .setAmountInWords(NumberToWordsConverter.convertToWords(request.getTotalAmount()));
            deliveryChallanEntity.setMessage("Delivery Challan created successfully");
            deliveryChallanEntity.setSuccess(true);
            deliveryChallanEntity.setTotalQuantity(request.getTotalQuantity());
            deliveryChallanEntity.setStatus(request.getStatus());
            deliveryChallanEntity.setCreatedAt(LocalDateTime.now().toString());
            deliveryChallanEntity.setUpdatedAt(LocalDateTime.now().toString());

            // Save proforma invoice first to get ID
            deliveryChallanEntity = deliveryChallanRepository.save(deliveryChallanEntity);

            // Process items
            List<NewDeliveryChallanResponse.NewDeliveryChallanItemResponse> responseItems = new ArrayList<>();

            for (NewDeliveryChallanRequest.NewDeliveryChallanItemRequest itemRequest : request.getItems()) {
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
                    NewDeliveryChallanItemEntity deliveryChallanItem = new NewDeliveryChallanItemEntity();
                    deliveryChallanItem.setDeliveryChallanId(deliveryChallanEntity.getId());
                    deliveryChallanItem.setItemId(itemRequest.getItemId());
                    deliveryChallanItem.setQuantity(itemRequest.getQuantity());
                    deliveryChallanItem.setPrice(itemRequest.getPrice());
                    deliveryChallanItem.setDiscountPercent(itemRequest.getDiscount());
                    deliveryChallanItem.setDiscountAmount(itemDiscountAmount);
                    deliveryChallanItem.setTotal(itemTotal);
                    deliveryChallanItem.setTaxAmount(itemRequest.getTaxAmount());
                    deliveryChallanItem.setTaxRateIndex(itemRequest.getTaxRateIndex());

                    // Set tax rate information
                    deliveryChallanItem.setTaxPercent(itemRequest.getTaxPercent());
                    deliveryChallanItem.setTaxRateId(itemRequest.getTaxRateId());

                    // Save estimate/quotation item
                    deliveryChallanItemRepository.save(deliveryChallanItem);

                    // Create response Item
                    NewDeliveryChallanResponse.NewDeliveryChallanItemResponse responseItem = new NewDeliveryChallanResponse.NewDeliveryChallanItemResponse();
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
            deliveryChallanRepository.save(deliveryChallanEntity);
            response.setDeliveryChallanId(deliveryChallanEntity.getId());
            response.setDeliveryChallanNumber(deliveryChallanNumber);
            response.setDeliveryChallanInvoiceDate(
                    LocalDate.parse(deliveryChallanEntity.getDeliveryChallanInvoiceDate()));
            response.setDeliveryChallanDueDate(
                    LocalDate.parse(deliveryChallanEntity.getDeliveryChallanDueDate()));
            response.setBillingAddress(deliveryChallanEntity.getBillingAddress());
            response.setPartyName(request.getPartyName());
            response.setPartyPhone(request.getPartyPhone());
            response.setItems(responseItems);
            response.setTotalDiscountAmount(deliveryChallanEntity.getDiscountAmount());
            response.setTotalAmount(deliveryChallanEntity.getTotalAmount());
            response.setTotalTaxAmount(deliveryChallanEntity.getTotalTaxAmount());
            response.setTaxableAmount(deliveryChallanEntity.getTotalTaxAmount());
            response.setTaxableAmount(deliveryChallanEntity.getTaxableAmount());
            response.setAmountInWords(NumberToWordsConverter.convertToWords(totalAmount));
            response.setSuccess(true);
            response.setMessage("Delivery Challan created successfully");
            response.setTotalQuantity(deliveryChallanEntity.getTotalQuantity());
            response.setStatus(deliveryChallanEntity.getStatus());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating delivery challan: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    private String generateDeliveryChallanNumber() {
        Long latestId = deliveryChallanRepository.findMaxDeliveryChallanId();
        if (latestId == null) {
            latestId = 0L;
        }
        return String.format("DC-%05d", latestId + 1);
    }
}
