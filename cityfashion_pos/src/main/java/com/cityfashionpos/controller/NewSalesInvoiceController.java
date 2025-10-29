package com.cityfashionpos.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.NewSalesInvoiceRequest;
import com.cityfashionpos.dto.NewSalesInvoiceResponse;
import com.cityfashionpos.repository.NewSalesInvoiceRepository;
import com.cityfashionpos.service.NewSalesInvoiceService;

@RestController
@RequestMapping("/api/new-sales-invoice")
@CrossOrigin(origins = "*")
public class NewSalesInvoiceController {

    @Autowired
    private NewSalesInvoiceService newSalesInvoiceService;

    @Autowired
    private NewSalesInvoiceRepository newSalesInvoiceRepository;

    @PostMapping("/create")
    public ResponseEntity<NewSalesInvoiceResponse> createNewSalesInvoice(@RequestBody NewSalesInvoiceRequest request) {
        try {
            NewSalesInvoiceResponse response = newSalesInvoiceService.createNewSalesInvoice(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewSalesInvoiceResponse errorResponse = new NewSalesInvoiceResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/invoice-number")
    public ResponseEntity<Map<String, String>> generateInvoiceNumber() {
        Long latestId = newSalesInvoiceRepository.findMaxInvoiceId();
        if (latestId == null) {
            latestId = 0L;
        }
        String nextInvoiceNumber = String.format("RS-%05d", latestId + 1);
        Map<String, String> response = new HashMap<>();
        response.put("invoiceNumber", nextInvoiceNumber);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getInvoiceById/{id}")
    public ResponseEntity<NewSalesInvoiceResponse> getInvoiceById(@PathVariable Long id) {
        try {
            NewSalesInvoiceResponse response = newSalesInvoiceService.getSalesInvoiceById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewSalesInvoiceResponse errorResponse = new NewSalesInvoiceResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
