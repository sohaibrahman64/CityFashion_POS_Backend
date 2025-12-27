package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.PartyTransactionDTO;
import com.cityfashionpos.dto.PartiesReportResponse;
import com.cityfashionpos.service.PartyTransactionService;

@RestController
@RequestMapping("/api/party-transactions")
@CrossOrigin(origins = "*")
public class PartyTransactionController {

    @Autowired
    private PartyTransactionService partyTransactionService;

    /**
     * Create a new party transaction
     * POST /api/party-transactions/createTransaction
     */
    @PostMapping("/createTransaction")
    public ResponseEntity<PartyTransactionDTO> createTransaction(@RequestBody PartyTransactionDTO dto) {
        try {
            PartyTransactionDTO created = partyTransactionService.createTransaction(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all transactions for a specific party
     * GET /api/party-transactions/party/{partyId}
     */
    @GetMapping("/party/{partyId}")
    public ResponseEntity<List<PartyTransactionDTO>> getTransactionsByParty(@PathVariable Long partyId) {
        List<PartyTransactionDTO> list = partyTransactionService.getTransactionsByParty(partyId);
        return ResponseEntity.ok(list);
    }

    /**
     * Get parties report for a specific party
     * GET /api/party-transactions/report?partyId={partyId}
     */
    @GetMapping("/report")
    public ResponseEntity<List<PartiesReportResponse>> getPartiesReport(@RequestParam Long partyId) {
        try {
            List<PartiesReportResponse> report = partyTransactionService.getPartiesReport(partyId);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @GetMapping("/partial-or-unpaid")
    public ResponseEntity<List<PartiesReportResponse>> getPartialOrUnpaidTransactions(@RequestParam Long partyId) {
        try {
            List<PartiesReportResponse> transactions = partyTransactionService.getPartialOrUnpaidTransactions(partyId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}