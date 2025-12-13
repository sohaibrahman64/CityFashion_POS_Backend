package com.cityfashionpos.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityfashionpos.dto.PartiesReportResponse;
import com.cityfashionpos.dto.PartyTransactionDTO;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.PartyTransactionEntity;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.PartyTransactionRepository;
import com.cityfashionpos.service.PartyTransactionService;

@Service
public class PartyTransactionServiceImpl implements PartyTransactionService {

    @Autowired
    private PartyTransactionRepository partyTransactionRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Override
    @Transactional
    public PartyTransactionDTO createTransaction(PartyTransactionDTO dto) {
        if (dto.getPartyId() == null) {
            throw new IllegalArgumentException("partyId is required");
        }
        Optional<PartyEntity> partyOptional = partyRepository.findById(dto.getPartyId());
        PartyEntity party = partyOptional
                .orElseThrow(() -> new RuntimeException("Party not found with id: " + dto.getPartyId()));

        PartyTransactionEntity entity = new PartyTransactionEntity();
        entity.setParty(party);
        entity.setDescription(dto.getDescription());
        entity.setInvoiceId(dto.getInvoiceId());
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setPurchaseId(dto.getPurchaseId());
        entity.setPurchaseBillNumber(dto.getPurchaseBillNumber());
        entity.setPartyPhone(dto.getPartyPhone());
        entity.setPartyName(dto.getPartyName());
        entity.setReferenceId(dto.getReferenceId());
        entity.setReferenceNumber(dto.getReferenceNumber());
        entity.setReferenceType(dto.getReferenceType());
        entity.setStatus(dto.getStatus());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedAt(dto.getCreatedAt().toString());
        entity.setUpdatedBy(dto.getUpdatedBy());
        entity.setUpdatedAt(dto.getUpdatedAt().toString());
        entity.setTransactionType(dto.getTransactionType());
        entity.setDate(dto.getDate().toString());
        entity.setPartyTotal(dto.getPartyTotal());
        entity.setPartyBalance(dto.getPartyBalance());

        PartyTransactionEntity saved = partyTransactionRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public List<PartyTransactionDTO> getTransactionsByParty(Long partyId) {
        return partyTransactionRepository.findByParty_Id(partyId).stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PartiesReportResponse> getPartiesReport(Long partyId) {
        return partyTransactionRepository.findByParty_Id(partyId).stream()
                .map(this::toReportDto)
                .collect(Collectors.toList());
    }

    private PartyTransactionDTO toDto(PartyTransactionEntity entity) {
        PartyTransactionDTO dto = new PartyTransactionDTO();
        dto.setId(entity.getId());
        dto.setPartyId(entity.getParty().getId());
        dto.setPartyName(entity.getParty().getPartyName());
        dto.setTransactionType(entity.getTransactionType());
        dto.setDate(LocalDateTime.parse(entity.getDate()));
        dto.setPartyTotal(entity.getPartyTotal());
        dto.setPartyBalance(entity.getPartyBalance());
        dto.setStatus(entity.getStatus());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(LocalDateTime.parse(entity.getCreatedAt()));
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedAt(LocalDateTime.parse(entity.getUpdatedAt()));
        return dto;
    }

    private PartiesReportResponse toReportDto(PartyTransactionEntity entity) {
        PartiesReportResponse reportDto = new PartiesReportResponse();
        reportDto.setId(entity.getId());
        reportDto.setInvoiceId(entity.getInvoiceId());
        reportDto.setDate(LocalDateTime.parse(entity.getDate()));
        reportDto.setPartyName(entity.getParty().getPartyName());
        reportDto.setInvoiceNumber(entity.getInvoiceNumber());
        reportDto.setPurchaseBillNumber(entity.getPurchaseBillNumber());
        reportDto.setReferenceNumber(entity.getReferenceNumber());
        reportDto.setTransactionType(entity.getTransactionType());
        reportDto.setPartyTotal(entity.getPartyTotal());
        reportDto.setPartyBalance(entity.getPartyBalance());
        reportDto.setStatus(entity.getStatus());
        return reportDto;
    }
}
