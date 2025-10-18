package com.cityfashionpos.service;

import java.util.List;

import com.cityfashionpos.dto.PartyTransactionDTO;
import com.cityfashionpos.dto.PartiesReportResponse;

public interface PartyTransactionService {

    PartyTransactionDTO createTransaction(PartyTransactionDTO dto);

    List<PartyTransactionDTO> getTransactionsByParty(Long partyId);

    List<PartiesReportResponse> getPartiesReport(Long partyId);
}
