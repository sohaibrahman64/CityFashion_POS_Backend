package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.PurchaseDTO;
import com.cityfashionpos.entity.PurchaseEntity;
import com.cityfashionpos.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {
	@Autowired private PurchaseService purchaseService;
	
	@PostMapping("/addPurchase")
    public ResponseEntity<?> addPurchase(@RequestBody PurchaseDTO dto) {
        PurchaseEntity purchase = purchaseService.createPurchase(dto);
        return ResponseEntity.ok(purchase);
    }
	
    @GetMapping("/getPurchaseHistory")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseHistory() {
        List<PurchaseDTO> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }
}
