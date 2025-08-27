package com.cityfashionpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.NewSalesInvoiceRequest;
import com.cityfashionpos.dto.NewSalesInvoiceResponse;
import com.cityfashionpos.service.NewSalesInvoiceService;

@RestController
@RequestMapping("/api/new-sales-invoice")
@CrossOrigin(origins = "*")
public class NewSalesInvoiceController {

    @Autowired
    private NewSalesInvoiceService newSalesInvoiceService;

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
}
