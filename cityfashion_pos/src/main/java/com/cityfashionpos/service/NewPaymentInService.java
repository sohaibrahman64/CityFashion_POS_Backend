package com.cityfashionpos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.NewPaymentInRequest;
import com.cityfashionpos.dto.NewPaymentInResponse;
import com.cityfashionpos.entity.NewPaymentInEntity;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.PaymentTypesEntity;
import com.cityfashionpos.repository.NewPaymentInRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.PaymentTypesRepository;

@Service
public class NewPaymentInService {

    @Autowired
    private NewPaymentInRepository paymentInRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PaymentTypesRepository paymentTypesRepository;

    @Transactional
    public NewPaymentInResponse createNewPaymentIn(NewPaymentInRequest request) {
        NewPaymentInResponse response = new NewPaymentInResponse();

        try {
            NewPaymentInEntity paymentInEntity = new NewPaymentInEntity();
            Optional<PartyEntity> partyOpt = partyRepository.findById(request.getPartyId());
            if (partyOpt.isPresent()) {
                PartyEntity party = partyOpt.get();
                paymentInEntity.setParty(party);

                // Update the balance
                BigDecimal currentBalance = party.getUpdatedBalance() != null ? party.getUpdatedBalance()
                        : BigDecimal.ZERO;
                BigDecimal newBalance = currentBalance.subtract(request.getReceivedAmount());
                party.setUpdatedBalance(newBalance);

                // Persist the updated party
                partyRepository.save(party);
            }

            paymentInEntity.setPaymentReceivedDate(request.getReceivedDate());
            paymentInEntity.setReceiptNumber(request.getReceiptNumber());
            paymentInEntity.setDescription(request.getDescription());
            paymentInEntity.setReceivedAmount(request.getReceivedAmount());
            Optional<PaymentTypesEntity> paymentTypes = paymentTypesRepository.findById(request.getPaymentTypeId());
            paymentTypes.ifPresent(paymentInEntity::setPaymentType);
            // paymentInEntity.setPaymentReceivedDate(LocalDate.now().toString());
            paymentInEntity.setCreatedAt(LocalDateTime.now().toString());
            paymentInEntity.setUpdatedAt(LocalDateTime.now().toString());

            paymentInEntity = paymentInRepository.save(paymentInEntity);

            // Create a response
            if (paymentInEntity.getParty() != null) {
                NewPaymentInResponse.PartyInfo partyInfo = new NewPaymentInResponse.PartyInfo(
                        paymentInEntity.getParty().getId(), paymentInEntity.getParty().getPartyName(),
                        paymentInEntity.getParty().getOpeningBalance().doubleValue(),
                        paymentInEntity.getParty().getPhoneNumber(),
                        paymentInEntity.getParty().getBillingAddress(),
                        paymentInEntity.getParty().getState().getState());
                response.setPartyInfo(partyInfo);
            }

            if (paymentInEntity.getPaymentType() != null) {
                NewPaymentInResponse.PaymentTypeInfo paymentTypeInfo = new NewPaymentInResponse.PaymentTypeInfo(
                        paymentInEntity.getPaymentType().getId(), paymentInEntity.getPaymentType().getPaymentType());
                response.setPaymentTypeInfo(paymentTypeInfo);
            }

            response.setDescription(paymentInEntity.getDescription());
            response.setReceivedAmount(paymentInEntity.getReceivedAmount());
            response.setReceiptNumber(paymentInEntity.getReceiptNumber());
            response.setReceivedDate(paymentInEntity.getPaymentReceivedDate());
            response.setSuccess(true);
            response.setMessage("Payment In Transaction recorded successfully");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error recording payment in transaction:  " + e.getMessage());
        }

        return response;
    }

}
