package com.cityfashionpos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityfashionpos.dto.NewSalesReturnRequest;
import com.cityfashionpos.dto.NewSalesReturnResponse;
import com.cityfashionpos.entity.ItemEntity;
import com.cityfashionpos.entity.NewSalesReturnEntity;
import com.cityfashionpos.entity.NewSalesReturnItemEntity;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.repository.ItemRepository;
import com.cityfashionpos.repository.NewSalesReturnItemRepository;
import com.cityfashionpos.repository.NewSalesReturnRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.TaxRateRepository;
import com.cityfashionpos.utils.NumberToWordsConverter;

@Service
public class NewSalesReturnService {

    @Autowired
    private NewSalesReturnRepository salesReturnRepository;

    @Autowired
    private NewSalesReturnItemRepository salesReturnItemRepository;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private TaxRateService taxRateService;

    @Transactional
    public NewSalesReturnResponse createNewSalesReturn(NewSalesReturnRequest request) {
        NewSalesReturnResponse response = new NewSalesReturnResponse();

        try {
            String salesReturnNumber = generateSalesReturnNumber();

            // double subtotalAmount = 0.0;
            // double totalDiscountAmount = 0.0;
            double totalAmount = 0.0;

            NewSalesReturnEntity salesReturnEntity = new NewSalesReturnEntity();
            salesReturnEntity.setInvoiceId(request.getInvoiceId());
            salesReturnEntity.setInvoiceNumber(request.getInvoiceNumber());
            salesReturnEntity
                    .setInvoiceDate(request.getInvoiceDate() != null ? request.getInvoiceDate().toString() : null);
            salesReturnEntity.setSalesReturnNumber(salesReturnNumber);
            salesReturnEntity.setPartyId(request.getPartyId());
            salesReturnEntity.setSalesReturnDate(LocalDate.now().toString());
            salesReturnEntity.setTotalAmount(request.getTotalAmount());
            salesReturnEntity.setSubtotalAmount(request.getSubtotalAmount());
            salesReturnEntity
                    .setPaidAmount(request.getPaidAmount() != null ? request.getPaidAmount() : 0.0);
            salesReturnEntity.setBalanceAmount(request.getBalanceAmount());
            salesReturnEntity.setDiscountAmount(request.getDiscountAmount());
            salesReturnEntity.setTotalTaxAmount(request.getTotalTaxAmount());
            salesReturnEntity.setTaxableAmount(request.getTaxableAmount());
            salesReturnEntity.setAmountInWords(NumberToWordsConverter.convertToWords(request.getTotalAmount()));
            salesReturnEntity.setMessage("Sales return created successfully");
            salesReturnEntity.setSuccess(true);
            salesReturnEntity.setBillingAddress(request.getBillingAddress());
            salesReturnEntity.setShippingAddress(request.getShippingAddress());

            salesReturnEntity = salesReturnRepository.save(salesReturnEntity);

            List<NewSalesReturnResponse.NewSalesReturnItemResponse> responseItems = new ArrayList<>();

            for (NewSalesReturnRequest.NewSalesReturnItemRequest itemRequest : request.getItems()) {
                if (itemRequest.getItemName() != null && !itemRequest.getItemName().trim().isEmpty()) {
                    // TaxRateEntity taxRate = null;
                    // java.math.BigDecimal taxPercent = java.math.BigDecimal.ZERO;

                    if (itemRequest.getTaxRateId() != null) {
                        if (!taxRateService.isTaxRateActive(itemRequest.getTaxRateId())) {
                            throw new IllegalArgumentException(
                                    "Invalid or inactive tax rate ID: " + itemRequest.getTaxRateId());
                        }
                        // taxRate = taxRateService.getTaxRateById(itemRequest.getTaxRateId());
                        // if (taxRate != null) {
                        // taxPercent = taxRate.getRate();
                        // }
                    }

                    double itemSubtotal = (itemRequest.getQuantity() != null ? itemRequest.getQuantity() : 0) *
                            (itemRequest.getPrice() != null ? itemRequest.getPrice() : 0);
                    double itemDiscountAmount = itemSubtotal
                            * (itemRequest.getDiscount() != null ? itemRequest.getDiscount() : 0) / 100;
                    double itemTotal = itemSubtotal - itemDiscountAmount;

                    // subtotalAmount += itemSubtotal;
                    // totalDiscountAmount += itemDiscountAmount;
                    totalAmount += itemTotal;

                    NewSalesReturnItemEntity salesReturnItem = new NewSalesReturnItemEntity();
                    salesReturnItem.setSalesReturnId(salesReturnEntity.getId());
                    salesReturnItem.setItemId(itemRequest.getItemId());
                    salesReturnItem.setQuantity(itemRequest.getQuantity());
                    salesReturnItem.setPrice(itemRequest.getPrice());
                    salesReturnItem.setDiscountPercent(itemRequest.getDiscount());
                    salesReturnItem.setDiscountAmount(itemRequest.getDiscountAmount());
                    salesReturnItem.setTotal(itemRequest.getTotal());
                    salesReturnItem.setTaxAmount(itemRequest.getTaxAmount());
                    salesReturnItem.setTaxPercent(itemRequest.getTaxPercent());
                    salesReturnItem.setTaxRateId(itemRequest.getTaxRateId());

                    salesReturnItemRepository.save(salesReturnItem);

                    NewSalesReturnResponse.NewSalesReturnItemResponse responseItem = new NewSalesReturnResponse.NewSalesReturnItemResponse();
                    responseItem.setId(itemRequest.getId());
                    responseItem.setItemName(itemRequest.getItemName());
                    responseItem.setHsnCode("HSN Code");
                    responseItem.setQuantity(itemRequest.getQuantity());
                    responseItem.setPrice(itemRequest.getPrice());
                    responseItem.setDiscount(itemRequest.getDiscount());
                    responseItem.setDiscountAmount(itemRequest.getDiscountAmount());
                    responseItem.setTotal(itemRequest.getTotal());
                    responseItem.setTaxAmount(itemRequest.getTaxAmount());
                    responseItem.setTaxPercent(itemRequest.getTaxPercent());
                    if (itemRequest.getTaxRateId() != null) {
                        responseItem.setTaxRate(taxRateRepository.findById(itemRequest.getTaxRateId()).orElse(null));
                    }

                    responseItems.add(responseItem);
                }
            }

            salesReturnRepository.save(salesReturnEntity);

            response.setSalesReturnId(salesReturnEntity.getId());
            response.setSalesReturnNumber(salesReturnNumber);
            if (salesReturnEntity.getSalesReturnDate() != null) {
                response.setSalesReturnDate(LocalDate.parse(salesReturnEntity.getSalesReturnDate()));
            }
            response.setInvoiceId(salesReturnEntity.getInvoiceId());
            response.setInvoiceNumber(salesReturnEntity.getInvoiceNumber());
            response.setInvoiceDate(
                    salesReturnEntity.getInvoiceDate() != null ? LocalDate.parse(salesReturnEntity.getInvoiceDate())
                            : null);
            response.setPartyName(request.getPartyName());
            response.setPartyPhone(request.getPartyPhone());
            response.setItems(responseItems);
            response.setSubtotalAmount(salesReturnEntity.getSubtotalAmount());
            response.setTotalDiscountAmount(salesReturnEntity.getDiscountAmount());
            response.setTotalAmount(salesReturnEntity.getTotalAmount());
            response.setPaidAmount(salesReturnEntity.getPaidAmount());
            response.setBalanceAmount(salesReturnEntity.getBalanceAmount());
            response.setDiscountAmount(salesReturnEntity.getDiscountAmount());
            response.setTotalTaxAmount(salesReturnEntity.getTotalTaxAmount());
            response.setTaxableAmount(salesReturnEntity.getTaxableAmount());
            response.setAmountInWords(NumberToWordsConverter.convertToWords(totalAmount));
            response.setSuccess(true);
            response.setMessage("Sales return created successfully");
            response.setBillingAddress(salesReturnEntity.getBillingAddress());
            response.setShippingAddress(salesReturnEntity.getShippingAddress());

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error creating sales return: " + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    private String generateSalesReturnNumber() {
        Long latestId = salesReturnRepository.findMaxSalesReturnId();
        if (latestId == null) {
            latestId = 0L;
        }
        return String.format("CRED-%05d", latestId + 1);
    }

    public NewSalesReturnResponse getSalesReturnById(Long salesReturnId) {
        NewSalesReturnResponse response = new NewSalesReturnResponse();

        try {
            NewSalesReturnEntity salesReturn = salesReturnRepository.findById(salesReturnId)
                    .orElseThrow(
                            () -> new IllegalArgumentException("Sales return not found with ID: " + salesReturnId));

            List<NewSalesReturnItemEntity> salesReturnItems = salesReturnItemRepository
                    .findBySalesReturnId(salesReturnId);
            List<NewSalesReturnResponse.NewSalesReturnItemResponse> responseItems = new ArrayList<>();
            for (NewSalesReturnItemEntity salesReturnItem : salesReturnItems) {
                NewSalesReturnResponse.NewSalesReturnItemResponse responseItem = new NewSalesReturnResponse.NewSalesReturnItemResponse();
                Optional<ItemEntity> itemEntity = itemRepository.findById(salesReturnItem.getItemId());
                responseItem.setId(salesReturnItem.getId());
                responseItem.setItemName(itemEntity.isPresent() ? itemEntity.get().getName() : null);
                responseItem.setHsnCode("HSN Code");
                responseItem.setQuantity(salesReturnItem.getQuantity());
                responseItem.setPrice(salesReturnItem.getPrice());
                responseItem.setDiscount(salesReturnItem.getDiscountPercent());
                responseItem.setDiscountAmount(salesReturnItem.getDiscountAmount());
                responseItem.setTotal(salesReturnItem.getTotal());
                responseItem.setTaxAmount(salesReturnItem.getTaxAmount());
                responseItem.setTaxPercent(salesReturnItem.getTaxPercent());
                if (salesReturnItem.getTaxRateId() != null) {
                    responseItem.setTaxRate(taxRateRepository.findById(salesReturnItem.getTaxRateId()).orElse(null));
                }
                responseItems.add(responseItem);
            }

            PartyEntity party = partyRepository.findById(salesReturn.getPartyId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Party not found with ID: " + salesReturn.getPartyId()));

            response.setSalesReturnId(salesReturn.getId());
            response.setSalesReturnNumber(salesReturn.getSalesReturnNumber());
            if (salesReturn.getSalesReturnDate() != null) {
                response.setSalesReturnDate(LocalDate.parse(salesReturn.getSalesReturnDate()));
            }
            response.setParty(partyRepository.findById(salesReturn.getPartyId()).get());
            response.setPartyName(party.getPartyName());
            response.setPartyPhone(party.getPhoneNumber());
            response.setItems(responseItems);
            response.setSubtotalAmount(salesReturn.getSubtotalAmount());
            response.setTotalDiscountAmount(salesReturn.getDiscountAmount());
            response.setTotalAmount(salesReturn.getTotalAmount());
            response.setPaidAmount(salesReturn.getPaidAmount());
            response.setBalanceAmount(salesReturn.getBalanceAmount());
            response.setDiscountAmount(salesReturn.getDiscountAmount());
            response.setTotalTaxAmount(salesReturn.getTotalTaxAmount());
            response.setTaxableAmount(salesReturn.getTaxableAmount());
            response.setAmountInWords(salesReturn.getAmountInWords());
            response.setSuccess(true);
            response.setMessage("Sales return fetched successfully");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error fetching sales return: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

}
