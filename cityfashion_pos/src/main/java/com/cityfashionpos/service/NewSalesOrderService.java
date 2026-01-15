package com.cityfashionpos.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.NewSalesOrderRequest;
import com.cityfashionpos.dto.NewSalesOrderResponse;
import com.cityfashionpos.entity.NewSalesOrderEntity;
import com.cityfashionpos.entity.NewSalesOrderItemEntity;
import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.repository.NewSalesOrderItemRepository;
import com.cityfashionpos.repository.NewSalesOrderRepository;
import com.cityfashionpos.repository.TaxRateRepository;
import com.cityfashionpos.utils.NumberToWordsConverter;

@Service
public class NewSalesOrderService {

    @Autowired
    private TaxRateService taxRateService;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private NewSalesOrderRepository salesOrderRepository;

    @Autowired
    private NewSalesOrderItemRepository salesOrderItemRepository;

    @Transactional
    public NewSalesOrderResponse createNewSalesOrder(NewSalesOrderRequest request) {
        NewSalesOrderResponse response = new NewSalesOrderResponse();

        try {
            // Generate proforma invoice number
            String salesOrderNumber = generateSalesOrderNumber();
            double totalAmount = 0.0;

            // Create proforma invoice entity and set fields
            NewSalesOrderEntity salesOrderEntity = new NewSalesOrderEntity();
            salesOrderEntity.setSalesOrderNumber(salesOrderNumber);
            salesOrderEntity.setPartyId(request.getPartyId());
            salesOrderEntity.setOrderDate(LocalDate.now().toString());
            salesOrderEntity.setDueDate(request.getDueDate().toString());
            salesOrderEntity.setTotalAmount(request.getTotalAmount());
            salesOrderEntity.setAdvanceAmount(request.getAdvanceAmount());
            salesOrderEntity.setBalanceAmount(request.getBalanceAmount());
            salesOrderEntity.setDiscountAmount(request.getDiscountAmount());
            salesOrderEntity.setTotalTaxAmount(request.getTotalTaxAmount());
            salesOrderEntity.setTaxableAmount(request.getTaxableAmount());
            salesOrderEntity
                    .setAmountInWords(NumberToWordsConverter.convertToWords(request.getTotalAmount()));
            salesOrderEntity.setMessage("Sales Order created successfully");
            salesOrderEntity.setSuccess(true);
            salesOrderEntity.setTotalQuantity(request.getTotalQuantity());
            salesOrderEntity.setStatus(request.getStatus());
            salesOrderEntity.setOrderDate(request.getOrderDate());
            salesOrderEntity.setDueDate(request.getDueDate());
            salesOrderEntity.setCreatedAt(LocalDateTime.now().toString());
            salesOrderEntity.setUpdatedAt(LocalDateTime.now().toString());

            // Save proforma invoice first to get ID
            salesOrderEntity = salesOrderRepository.save(salesOrderEntity);

            // Process items
            List<NewSalesOrderResponse.NewSalesOrderItemResponse> responseItems = new ArrayList<>();

            for (NewSalesOrderRequest.NewSalesOrderItemRequest itemRequest : request.getItems()) {
                if (itemRequest.getItemName() != null && !itemRequest.getItemName().trim().isEmpty()) {
                    // Validate and get tax rate if provided
                    TaxRateEntity taxRate = null;

                    // java.math.BigDecimal taxPercent = java.math.BigDecimal.ZERO;
                    if (itemRequest.getTaxRateId() != null) {
                        // Validate tax rate esitst and is active
                        if (!taxRateService.isTaxRateActive(itemRequest.getTaxRateId())) {
                            throw new IllegalArgumentException(
                                    "Invalid or inactive tax rate with id: " + itemRequest.getTaxRateId());
                        }
                        taxRate = taxRateService.getTaxRateById(itemRequest.getTaxRateId());
                        // if (taxRate != null) {
                        // taxPercent = taxRate.getRate();
                        // }
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
                    NewSalesOrderItemEntity salesOrderItemEntity = new NewSalesOrderItemEntity();
                    salesOrderItemEntity.setSalesOrderId(salesOrderEntity.getId());
                    salesOrderItemEntity.setItemId(itemRequest.getItemId());
                    salesOrderItemEntity.setQuantity(itemRequest.getQuantity());
                    salesOrderItemEntity.setPrice(itemRequest.getPrice());
                    salesOrderItemEntity.setDiscountPercent(itemRequest.getDiscount());
                    salesOrderItemEntity.setDiscountAmount(itemDiscountAmount);
                    salesOrderItemEntity.setTotal(itemTotal);
                    salesOrderItemEntity.setTaxAmount(itemRequest.getTaxAmount());
                    salesOrderItemEntity.setTaxRateIndex(itemRequest.getTaxRateIndex());

                    // Set tax rate information
                    salesOrderItemEntity.setTaxPercent(itemRequest.getTaxPercent());
                    salesOrderItemEntity.setTaxRateId(itemRequest.getTaxRateId());

                    // Save estimate/quotation item
                    salesOrderItemRepository.save(salesOrderItemEntity);

                    // Create response Item
                    NewSalesOrderResponse.NewSalesOrderItemResponse responseItem = new NewSalesOrderResponse.NewSalesOrderItemResponse();
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
            salesOrderRepository.save(salesOrderEntity);
            response.setSalesOrderId(salesOrderEntity.getId());
            response.setSalesOrderNumber(salesOrderNumber);
            response.setOrderDate(LocalDate.parse(salesOrderEntity.getOrderDate()));
            response.setPartyName(request.getPartyName());
            response.setPartyPhone(request.getPartyPhone());
            response.setItems(responseItems);
            response.setTotalDiscountAmount(salesOrderEntity.getDiscountAmount());
            response.setTotalAmount(salesOrderEntity.getTotalAmount());
            response.setAdvanceAmount(salesOrderEntity.getAdvanceAmount());
            response.setBalanceAmount(salesOrderEntity.getBalanceAmount());
            response.setTotalTaxAmount(salesOrderEntity.getTotalTaxAmount());
            response.setTaxableAmount(salesOrderEntity.getTotalTaxAmount());
            response.setTaxableAmount(salesOrderEntity.getTaxableAmount());
            response.setOrderDate(LocalDate.parse(salesOrderEntity.getOrderDate()));
            response.setDueDate(LocalDate.parse(salesOrderEntity.getDueDate()));
            response.setAmountInWords(NumberToWordsConverter.convertToWords(totalAmount));
            response.setSuccess(true);
            response.setMessage("Sales Order created successfully");
            response.setTotalQuantity(salesOrderEntity.getTotalQuantity());
            response.setStatus(salesOrderEntity.getStatus());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating sales order: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    private String generateSalesOrderNumber() {
        Long latestId = salesOrderRepository.findMaxSalesOrderId();
        if (latestId == null) {
            latestId = 0L;
        }
        return String.format("ORD-%05d", latestId + 1);
    }
}
