package com.cityfashionpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.NewProformaInvoiceRequest;
import com.cityfashionpos.dto.NewProformaInvoiceResponse;
import com.cityfashionpos.repository.NewProformaInvoiceRepository;
import com.cityfashionpos.service.NewProformaInvoiceService;

@RestController
@RequestMapping("/api/proforma-invoice")
@CrossOrigin(origins = "*")
public class NewProformaInvoiceController {

    @Autowired
    private NewProformaInvoiceService newProformaInvoiceService;

    @Autowired
    private NewProformaInvoiceRepository newProformaInvoiceRepository;

    @PostMapping("/create")
    public ResponseEntity<NewProformaInvoiceResponse> createNewProformaInvoice(NewProformaInvoiceRequest request) {
        try {
            NewProformaInvoiceResponse response = newProformaInvoiceService.createNewProformaInvoice(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewProformaInvoiceResponse errorResponse = new NewProformaInvoiceResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
