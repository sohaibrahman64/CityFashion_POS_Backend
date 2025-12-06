package com.cityfashionpos.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.EstimateQuotationTransactionRequest;
import com.cityfashionpos.dto.EstimateQuotationTransactionResponse;
import com.cityfashionpos.dto.SalesTransactionResponse;
import com.cityfashionpos.entity.EstimateQuotationTransactionEntity;
import com.cityfashionpos.repository.EstimateQuotationTransactionRepository;
import com.cityfashionpos.repository.NewEstimateQuotationRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.service.EstimateQuotationTransactionService;

@Service
@Transactional
public class EstimateQuotationTransactionServiceImpl implements EstimateQuotationTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(EstimateQuotationTransactionServiceImpl.class);

    @Autowired
    private EstimateQuotationTransactionRepository estimateQuotationTransactionRepository;

    @Autowired
    private NewEstimateQuotationRepository estimateQuotationRespository;

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public EstimateQuotationTransactionResponse createEstimateQuotationTransaction(
            EstimateQuotationTransactionRequest request) {
        try {
            logger.info("Creating estimate quotation transaction for amount: {}", request.getTotalAmount());

            // Generate transaction number if not provided
            if (request.getTransactionNumber() == null || request.getTransactionNumber().isEmpty()) {
                request.setTransactionNumber(generateEstimateQuotationTransactionNumber());
            }
            EstimateQuotationTransactionEntity entity = mapRequestToEntity(request);

            entity = estimateQuotationTransactionRepository.save(entity);

            logger.info("Estimate Quotation Transaction created successfully with ID: {}", entity.getId());
            return mapEntityToResponse(entity, true, "Estimate Quotation Transaction created successfully");
        } catch (Exception e) {
            logger.error("Error creating sales transaction: {}", e.getMessage(), e);
            return new EstimateQuotationTransactionResponse(false,
                    "Error creating sales transaction: " + e.getMessage());
        }

    }

    @Override
    public List<EstimateQuotationTransactionResponse> getAllEstimateQuotationTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllEstimateQuotationTransactions'");
    }

    @Override
    public String generateEstimateQuotationTransactionNumber() {
        Long maxId = estimateQuotationTransactionRepository.findMaxTransactionId();
        if (maxId == null) {
            maxId = 0L;
        }
        return String.format("EQT-%05d", maxId + 1);
    }

    // Helper methods
    private EstimateQuotationTransactionEntity mapRequestToEntity(EstimateQuotationTransactionRequest request) {
        EstimateQuotationTransactionEntity entity = new EstimateQuotationTransactionEntity();

        entity.setTransactionNumber(request.getTransactionNumber());
        entity.setEstimateQuotationId(request.getEstimateQuotationId());
        entity.setEstimateQuotationNumber(request.getEstimateNumber());
        entity.setPartyId(request.getPartyId());
        entity.setPartyName(request.getPartyName());
        entity.setTransactionDate(request.getTransactionDate());
        entity.setTransactionTime(request.getTransactionTime());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setTaxAmount(request.getTaxAmount());
        entity.setDiscountAmount(request.getDiscountAmount());
        entity.setItemCount(request.getItemCount());
        entity.setTotalQuantity(request.getTotalQuantity());
        entity.setNotes(request.getNotes());
        entity.setCreatedBy(request.getCreatedBy());
        entity.setBalanceAmount(request.getBalanceAmount());

        return entity;
    }

    private EstimateQuotationTransactionResponse mapEntityToResponse(EstimateQuotationTransactionEntity entity,
            boolean success, String message) {
        EstimateQuotationTransactionResponse response = new EstimateQuotationTransactionResponse(success, message);

        response.setId(entity.getId());
        response.setTransactionNumber(entity.getTransactionNumber());
        response.setEstimateQuotationId(entity.getEstimateQuotationId());
        response.setEstimateQuotationNumber(entity.getEstimateQuotationNumber());
        response.setPartyId(entity.getPartyId());
        response.setPartyName(entity.getPartyName());
        response.setTransactionDate(entity.getTransactionDate());
        response.setTransactionTime(entity.getTransactionTime());
        response.setTotalAmount(entity.getTotalAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setNotes(entity.getNotes());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());

        response.setMessage(message);
        response.setSuccess(success);
        response.setBalanceAmount(entity.getBalanceAmount());

        return response;
    }
}
