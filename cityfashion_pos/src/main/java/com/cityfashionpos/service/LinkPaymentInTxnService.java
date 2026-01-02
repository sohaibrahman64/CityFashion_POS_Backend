package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.LinkPaymentInRequest;
import com.cityfashionpos.dto.LinkPaymentInResponse;
import com.cityfashionpos.entity.LinkPaymentInTxnEntity;
import com.cityfashionpos.entity.LinkPaymentInItemEntity;
import com.cityfashionpos.entity.NewPaymentInEntity;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.PartyTransactionEntity;
import com.cityfashionpos.repository.LinkPaymentInItemRepository;
import com.cityfashionpos.repository.LinkPaymentInTxnRepository;
import com.cityfashionpos.repository.NewPaymentInRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.PartyTransactionRepository;

@Service
public class LinkPaymentInTxnService {
    @Autowired
    private LinkPaymentInTxnRepository linkPaymentInTxnRepository;

    @Autowired
    private LinkPaymentInItemRepository linkPaymentInItemRepository;

    @Autowired
    private NewPaymentInRepository paymentInRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PartyTransactionRepository partyTransactionRepository;

    public LinkPaymentInResponse createLinkPaymentIn(LinkPaymentInRequest request) {
        LinkPaymentInResponse response = new LinkPaymentInResponse();
        try {
            LinkPaymentInTxnEntity linkPaymentInEntity = new LinkPaymentInTxnEntity();
            Optional<NewPaymentInEntity> paymentInOpt = request.getPaymentInId() != null
                    ? paymentInRepository.findById(request.getPaymentInId())
                    : Optional.empty();
            if (paymentInOpt.isPresent()) {
                NewPaymentInEntity paymentIn = paymentInOpt.get();
                linkPaymentInEntity.setNewPaymentInEntity(paymentIn);
            }

            Optional<PartyEntity> partyOpt = request.getParty().getId() != null
                    ? partyRepository.findById(request.getParty().getId())
                    : Optional.empty();
            if (partyOpt.isPresent()) {
                PartyEntity party = partyOpt.get();
                linkPaymentInEntity.setPartyEntity(party);
            }

            linkPaymentInEntity.setUnusedAmount(request.getUnusedAmount());
            linkPaymentInEntity.setReceivedAmount(request.getReceivedAmount());
            linkPaymentInEntity.setCreatedAt(LocalDateTime.now().toString());
            linkPaymentInEntity.setUpdatedAt(LocalDateTime.now().toString());

            linkPaymentInEntity = linkPaymentInTxnRepository.save(linkPaymentInEntity);

            List<LinkPaymentInResponse.LinkedAmountItemResponse> responseItems = new ArrayList<>();

            for (LinkPaymentInRequest.LinkedAmountItem itemRequest : request.getLinkedAmountItems()) {
                if (itemRequest.getLinkedAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    continue; // Skip items with zero or negative linked amount
                }
                // Create link payment in item entity
                LinkPaymentInItemEntity linkPaymentInItemEntity = new LinkPaymentInItemEntity();
                Optional<PartyTransactionEntity> partyTransactionOpt = partyTransactionRepository
                        .findById(itemRequest.getPartyTransactionId());
                if (partyTransactionOpt.isPresent()) {
                    PartyTransactionEntity partyTransaction = partyTransactionOpt.get();
                    linkPaymentInItemEntity.setPartyTransactionEntity(partyTransaction);
                }
                linkPaymentInItemEntity.setLinkPaymentInTxnEntity(linkPaymentInEntity);
                linkPaymentInItemEntity.setReferenceNumber(itemRequest.getReferenceNumber());
                linkPaymentInItemEntity.setLinkedAmount(itemRequest.getLinkedAmount());

                // Create response item
                LinkPaymentInResponse.LinkedAmountItemResponse itemResponse = new LinkPaymentInResponse.LinkedAmountItemResponse();
                if (partyTransactionOpt.isPresent()) {
                    PartyTransactionEntity partyTransaction = partyTransactionOpt.get();
                    itemResponse.setPartyTransactionId(partyTransaction.getId());
                }
                itemResponse.setReferenceNumber(itemRequest.getReferenceNumber());
                itemResponse.setLinkedAmount(itemRequest.getLinkedAmount());
                responseItems.add(itemResponse);

                // Save Link Payment In Items
                linkPaymentInItemRepository.save(linkPaymentInItemEntity);
            }

            linkPaymentInEntity = linkPaymentInTxnRepository.save(linkPaymentInEntity);

            response.setLinkedAmountItems(responseItems);
            response.setLinkPaymentInTxnId(linkPaymentInEntity.getId());
            response.setPartyId(linkPaymentInEntity.getPartyEntity().getId());
            response.setPaymentInId(linkPaymentInEntity.getNewPaymentInEntity() != null
                    ? linkPaymentInEntity.getNewPaymentInEntity().getId()
                    : null);
            response.setReceivedAmount(linkPaymentInEntity.getReceivedAmount());
            response.setUnusedAmount(linkPaymentInEntity.getUnusedAmount());

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred: " + e.getMessage());
            return response;
        }
        // Assuming the operation is successful
        response.setSuccess(true);
        response.setMessage("Linking successful");
        return response;
    }
}
