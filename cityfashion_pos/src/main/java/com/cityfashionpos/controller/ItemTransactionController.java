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
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.ItemTransactionDTO;
import com.cityfashionpos.service.ItemTransactionService;

@RestController
@RequestMapping("/api/item-transactions")
@CrossOrigin(origins = "*")
public class ItemTransactionController {

    @Autowired
    private ItemTransactionService itemTransactionService;

    @PostMapping("/create")
    public ResponseEntity<List<ItemTransactionDTO>> createTransaction(
            @RequestBody List<ItemTransactionDTO> itemTransactionDTOs) {
        List<ItemTransactionDTO> createdTransactions = itemTransactionService
                .createTransactions(itemTransactionDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransactions);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<ItemTransactionDTO>> getTransactionsByItemId(
            @PathVariable Long itemId) {
        try {
            List<ItemTransactionDTO> transactions = itemTransactionService
                    .getTransactionsByItemId(itemId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
