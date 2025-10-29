package com.cityfashionpos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.dto.ItemRequestDTO;
import com.cityfashionpos.dto.ItemResponseDTO;
import com.cityfashionpos.entity.ItemEntity;
import com.cityfashionpos.service.ItemService;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Create a new item with optional image upload
     * POST /api/items
     */
    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody ItemRequestDTO itemDTO) {
        try {
            ItemEntity createdItem = itemService.saveItem(itemDTO, null);
            return ResponseEntity.ok(createdItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating item: " + e.getMessage());
        }
    }

    /**
     * Create or update item with image
     * PUT /api/items
     */
    @PutMapping(value = "/createItemWithImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createItemWithImage(
            @RequestPart("item") ItemRequestDTO itemDTO,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            ItemEntity item = itemService.saveItem(itemDTO, imageFile);
            return ResponseEntity.ok(item);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating item: " + e.getMessage());
        }
    }

    /**
     * Update an existing item
     * PUT /api/items/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(
            @PathVariable Long id,
            @RequestBody ItemRequestDTO itemDTO) {
        try {
            ItemEntity updatedItem = itemService.updateItem(id, itemDTO, null);
            return ResponseEntity.ok(updatedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating item: " + e.getMessage());
        }
    }

    /**
     * Update item with image
     * PUT /api/items/{id}/image
     */
    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateItemWithImage(
            @PathVariable Long id,
            @RequestPart("item") ItemRequestDTO itemDTO,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            ItemEntity updatedItem = itemService.updateItem(id, itemDTO, imageFile);
            return ResponseEntity.ok(updatedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating item: " + e.getMessage());
        }
    }

    /**
     * Get item by ID
     * GET /api/items/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        try {
            ItemEntity item = itemService.getItemById(id);
            return ResponseEntity.ok(item);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving item: " + e.getMessage());
        }
    }

    /**
     * Get item by code
     * GET /api/items/code/{code}
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<?> getItemByCode(@PathVariable String code) {
        try {
            ItemEntity item = itemService.getItemByCode(code);
            return ResponseEntity.ok(item);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving item: " + e.getMessage());
        }
    }

    /**
     * Delete item
     * DELETE /api/items/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting item: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllItems() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<ItemResponseDTO> items = itemService.getAllItems();
            response.put("items", items);
            response.put("success", true);
            response.put("count", items.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error retrieving products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/updateItemQuantity")
    public ResponseEntity<Map<String, Object>> updateItemQuantity(@RequestParam Long id,
            @RequestParam Integer quantity, @RequestParam String transactionType) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ItemResponseDTO> updatedItems = itemService.updateItemQuantity(id, quantity, transactionType);
            response.put("items", updatedItems);
            response.put("success", true);
            response.put("count", updatedItems.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error retrieving products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}