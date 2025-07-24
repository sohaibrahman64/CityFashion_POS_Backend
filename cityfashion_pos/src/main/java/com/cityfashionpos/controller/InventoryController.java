package com.cityfashionpos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.InventoryDTO;
import com.cityfashionpos.entity.InventoryEntity;
import com.cityfashionpos.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/saveInventory")
    public ResponseEntity<InventoryEntity> addInventory(@RequestBody InventoryDTO dto) {
        InventoryEntity saved = inventoryService.addInventory(dto);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<InventoryDTO>> getInventoryList() {
        return ResponseEntity.ok(inventoryService.getInventoryList());
    }
    
    @GetMapping("/checkByBarcode/{barcode}")
    public ResponseEntity<InventoryEntity> checkInventoryByBarcode(@PathVariable String barcode) {
        Optional<InventoryEntity> inventoryOpt = inventoryService.findByProductBarcode(barcode);
        if (inventoryOpt.isPresent()) {
            return ResponseEntity.ok(inventoryOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/updateInventory/{id}")
    public ResponseEntity<InventoryEntity> updateInventory(@PathVariable Long id, @RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, dto));
    }

}
