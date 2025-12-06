package com.cityfashionpos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.NewEstimateQuotationRequest;
import com.cityfashionpos.dto.NewEstimateQuotationResponse;
import com.cityfashionpos.entity.NewEstimateQuotationEntity;
import com.cityfashionpos.entity.NewEstimateQuotationItemEntity;
import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.repository.NewEstimateQuotationItemRepository;
import com.cityfashionpos.repository.NewEstimateQuotationRepository;
import com.cityfashionpos.repository.TaxRateRepository;
import com.cityfashionpos.utils.NumberToWordsConverter;

@Service
public class NewEstimateQuotationService {

    @Autowired
    private NewEstimateQuotationRepository estimateQuotationRepository;

    @Autowired
    private NewEstimateQuotationItemRepository estimateQuotationItemRepository;

    @Autowired
    private TaxRateService taxRateService;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Transactional
    public NewEstimateQuotationResponse createNewEstimateQuotation(NewEstimateQuotationRequest request) {
        NewEstimateQuotationResponse response = new NewEstimateQuotationResponse();

        try {
            // Generate estimate/quotation number
            String estimateQuotationNumber = generateEstimateQuotationNumber();
            double subtotalAmount = 0.0;
            double totalDiscountAmount = 0.0;
            double totalAmount = 0.0;

            // Create estimate/quotation entity and set fields
            NewEstimateQuotationEntity estimateQuotation = new NewEstimateQuotationEntity();
            estimateQuotation.setEstimateQuotationNumber(estimateQuotationNumber);
            estimateQuotation.setPartyId(request.getPartyId());
            estimateQuotation.setEstimateQuotationDate(LocalDate.now());
            estimateQuotation.setTotalAmount(request.getTotalAmount());
            estimateQuotation.setDiscountAmount(request.getDiscountAmount());
            estimateQuotation.setTotalTaxAmount(request.getTotalTaxAmount());
            estimateQuotation.setTaxableAmount(request.getTaxableAmount());
            estimateQuotation
                    .setAmountInWords(NumberToWordsConverter.convertToWords(request.getTotalAmount()));
            estimateQuotation.setMessage("Estimate/Quotation created successfully");
            estimateQuotation.setSuccess(true);
            estimateQuotation.setTotalQuantity(request.getTotalQuantity());

            // Save estimate/quotation first to get ID
            estimateQuotation = estimateQuotationRepository.save(estimateQuotation);

            // Process items
            List<NewEstimateQuotationResponse.NewEstimateQuotationItemResponse> responseItems = new ArrayList<>();

            for (NewEstimateQuotationRequest.NewEstimateQuotationItemRequest itemRequest : request.getItems()) {
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
                    subtotalAmount += itemSubtotal;
                    totalDiscountAmount += itemDiscountAmount;
                    totalAmount += itemTotal;

                    // Create estimate quotation item entity
                    NewEstimateQuotationItemEntity estimateQuotationItem = new NewEstimateQuotationItemEntity();
                    // estimateQuotationItem.setId(estimateQuotation.getId());
                    estimateQuotationItem.setEstimateQuotationId(estimateQuotation.getId());
                    estimateQuotationItem.setItemId(itemRequest.getItemId());
                    estimateQuotationItem.setQuantity(itemRequest.getQuantity());
                    estimateQuotationItem.setPrice(itemRequest.getPrice());
                    estimateQuotationItem.setDiscountPercent(itemRequest.getDiscount());
                    estimateQuotationItem.setDiscountAmount(itemDiscountAmount);
                    estimateQuotationItem.setTotal(itemTotal);
                    estimateQuotationItem.setTaxAmount(itemRequest.getTaxAmount());
                    estimateQuotationItem.setTaxRateIndex(itemRequest.getTaxRateIndex());

                    // Set tax rate information
                    estimateQuotationItem.setTaxPercent(itemRequest.getTaxPercent());
                    estimateQuotationItem.setTaxRateId(itemRequest.getTaxRateId());

                    // Save estimate/quotation item
                    estimateQuotationItemRepository.save(estimateQuotationItem);

                    // Create response Item
                    NewEstimateQuotationResponse.NewEstimateQuotationItemResponse responseItem = new NewEstimateQuotationResponse.NewEstimateQuotationItemResponse();
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
            estimateQuotationRepository.save(estimateQuotation);
            response.setEstimateQuotationId(estimateQuotation.getId());
            response.setEstimateQuotationNumber(estimateQuotationNumber);
            response.setEstimateQuotationDate(estimateQuotation.getEstimateQuotationDate());
            response.setPartyName(request.getPartyName());
            response.setPartyPhone(request.getPartyPhone());
            response.setItems(responseItems);
            response.setTotalDiscountAmount(estimateQuotation.getDiscountAmount());
            response.setTotalAmount(estimateQuotation.getTotalAmount());
            response.setTotalTaxAmount(estimateQuotation.getTotalTaxAmount());
            response.setTaxableAmount(estimateQuotation.getTotalTaxAmount());
            response.setTaxableAmount(estimateQuotation.getTaxableAmount());
            response.setAmountInWords(NumberToWordsConverter.convertToWords(totalAmount));
            response.setSuccess(true);
            response.setMessage("Estimate quotation created successfully");
            response.setTotalQuantity(estimateQuotation.getTotalQuantity());

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating estimate/quotation: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    private String generateEstimateQuotationNumber() {
        Long latestId = estimateQuotationRepository.findMaxEstimateQuotationId();
        if (latestId == null) {
            latestId = 0L;
        }
        return String.format("EQ-%05d", latestId + 1);
    }

}
