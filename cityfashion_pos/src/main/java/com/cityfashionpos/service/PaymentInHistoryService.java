package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.PaymentInHistoryRequest;
import com.cityfashionpos.dto.PaymentInHistoryResponse;
import com.cityfashionpos.entity.LinkPaymentInTxnEntity;
import com.cityfashionpos.entity.PaymentInHistoryEntity;
import com.cityfashionpos.entity.PaymentInHistoryItemEntity;
import com.cityfashionpos.repository.LinkPaymentInTxnRepository;
import com.cityfashionpos.repository.PaymentInHistoryItemRepository;
import com.cityfashionpos.repository.PaymentInHistoryRepository;

@Service
public class PaymentInHistoryService {

    @Autowired
    LinkPaymentInTxnRepository linkPaymentInTxnRepository;

    @Autowired
    PaymentInHistoryRepository paymentInHistoryRepository;

    @Autowired
    PaymentInHistoryItemRepository paymentInHistoryItemRepository;

    public PaymentInHistoryResponse createPaymentInHistory(PaymentInHistoryRequest request) {
        PaymentInHistoryResponse response = new PaymentInHistoryResponse();

        try {
            PaymentInHistoryEntity paymentInHistoryEntity = new PaymentInHistoryEntity();

            Optional<LinkPaymentInTxnEntity> linkPaymentInTxnOpt = request.getLinkPaymentInTxnId() != null
                    ? linkPaymentInTxnRepository.findById(request.getLinkPaymentInTxnId())
                    : Optional.empty();

            if (linkPaymentInTxnOpt.isPresent()) {
                LinkPaymentInTxnEntity linkPaymentInTxn = linkPaymentInTxnOpt.get();
                paymentInHistoryEntity.setLinkPaymentInTxn(linkPaymentInTxn);
            }

            paymentInHistoryEntity = paymentInHistoryRepository.save(paymentInHistoryEntity);

            List<PaymentInHistoryResponse.PaymentInHistoryItemResponse> responseItems = new ArrayList<>();
            for (PaymentInHistoryRequest.PaymentInHistoryRequestItem itemRequest : request
                    .getPaymentInHistoryRequestItems()) {
                if (itemRequest.getLinkedAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    continue; // Skip items with zero or negative linked amount
                }
                PaymentInHistoryItemEntity paymentInHistoryItemEntity = new PaymentInHistoryItemEntity();
                paymentInHistoryItemEntity.setReferenceNumber(itemRequest.getReferenceNumber());
                paymentInHistoryItemEntity.setLinkedAmount(itemRequest.getLinkedAmount());
                paymentInHistoryItemEntity.setTransactionType(itemRequest.getTransactionType());
                paymentInHistoryItemEntity.setPaymentInHistory(paymentInHistoryEntity);
                paymentInHistoryItemEntity.setTransactionDate(itemRequest.getTransactionDate());

                // Create response item
                PaymentInHistoryResponse.PaymentInHistoryItemResponse itemResponse = new PaymentInHistoryResponse.PaymentInHistoryItemResponse();
                itemResponse.setPaymentInHistoryId(paymentInHistoryItemEntity.getPaymentInHistory().getId());
                itemResponse.setReferenceNumber(itemRequest.getReferenceNumber());
                itemResponse.setLinkedAmount(itemRequest.getLinkedAmount());
                itemResponse.setTransactionType(itemRequest.getTransactionType());
                itemResponse.setTransactionDate(itemRequest.getTransactionDate());
                responseItems.add(itemResponse);

                // Save the item entity
                paymentInHistoryItemRepository.save(paymentInHistoryItemEntity);
            }

            paymentInHistoryEntity = paymentInHistoryRepository.save(paymentInHistoryEntity);

            response.setSuccess(true);
            response.setMessage("Payment in history created successfully");
            response.setPaymentInHistoryItems(responseItems);

            return response;

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("An error occurred while creating payment in history: " + e.getMessage());
            return response;
        }
    }

}
