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

import com.cityfashionpos.dto.NewSalesReturnRequest;
import com.cityfashionpos.dto.NewSalesReturnResponse;
import com.cityfashionpos.repository.NewSalesReturnRepository;
import com.cityfashionpos.service.NewSalesReturnService;

@RestController
@RequestMapping("/api/sales-return")
@CrossOrigin(origins = "*")
public class NewSalesReturnController {

    @Autowired
    private NewSalesReturnService newSalesReturnService;

    @Autowired
    private NewSalesReturnRepository newSalesReturnRepository;

    @PostMapping("/create")
    public ResponseEntity<NewSalesReturnResponse> createNewSalesReturn(@RequestBody NewSalesReturnRequest request) {
        try {
            NewSalesReturnResponse response = newSalesReturnService.createNewSalesReturn(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewSalesReturnResponse errorResponse = new NewSalesReturnResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/sales-return-number")
    public ResponseEntity<Map<String, String>> generateSalesReturnNumber() {
        Long latestId = newSalesReturnRepository.findMaxSalesReturnId();
        if (latestId == null) {
            latestId = 0L;
        }
        String nextSalesReturnNumber = String.format("CRED-%05d", latestId + 1);
        Map<String, String> response = new HashMap<>();
        response.put("salesReturnNumber", nextSalesReturnNumber);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getSalesReturnById/{id}")
    public ResponseEntity<NewSalesReturnResponse> getSalesReturnById(@PathVariable Long id) {
        try {
            NewSalesReturnResponse response = newSalesReturnService.getSalesReturnById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewSalesReturnResponse errorResponse = new NewSalesReturnResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
