package com.cityfashionpos.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.dto.ItemRequestDTO;
import com.cityfashionpos.dto.ItemResponseDTO;
import com.cityfashionpos.entity.ItemEntity;
import com.cityfashionpos.entity.TaxRateEntity;
import com.cityfashionpos.model.TransactionType;
import com.cityfashionpos.repository.ItemRepository;
import com.cityfashionpos.repository.TaxRateRepository;
import com.cityfashionpos.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Value("${product.image.upload-dir}")
    private String uploadDir;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Override
    public ItemEntity saveItem(ItemRequestDTO itemDTO, MultipartFile imageFile) {
        // Validate item code uniqueness
        if (itemDTO.getCode() != null && itemRepository.existsByCode(itemDTO.getCode())) {
            throw new IllegalArgumentException("Item code already exists");
        }

        // Create new item entity
        ItemEntity item = new ItemEntity();
        updateItemFromDTO(item, itemDTO);

        // Handle image upload if present
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            item.setImagePath(imagePath);
        }

        return itemRepository.save(item);
    }

    @Override
    public ItemEntity updateItem(Long id, ItemRequestDTO itemDTO, MultipartFile imageFile) {
        ItemEntity existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + id));

        // Check if new code conflicts with another item
        if (itemDTO.getCode() != null && !itemDTO.getCode().equals(existingItem.getCode())
                && itemRepository.existsByCode(itemDTO.getCode())) {
            throw new IllegalArgumentException("Item code already exists");
        }

        updateItemFromDTO(existingItem, itemDTO);

        // Handle image update if present
        if (imageFile != null && !imageFile.isEmpty()) {
            // Delete old image if exists
            if (existingItem.getImagePath() != null) {
                deleteImage(existingItem.getImagePath());
            }
            String imagePath = saveImage(imageFile);
            existingItem.setImagePath(imagePath);
        }

        return itemRepository.save(existingItem);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemEntity getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public ItemEntity getItemByCode(String code) {
        return itemRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with code: " + code));
    }

    @Override
    public void deleteItem(Long id) {
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + id));

        // Delete associated image if exists
        if (item.getImagePath() != null) {
            deleteImage(item.getImagePath());
        }

        itemRepository.delete(item);
    }

    private void updateItemFromDTO(ItemEntity item, ItemRequestDTO dto) {
        item.setName(dto.getName());
        item.setHsn(dto.getHsn());
        item.setUnit(dto.getUnit());
        item.setCategory(dto.getCategory());
        item.setCode(dto.getCode());

        // Update pricing details
        if (dto.getPricing() != null) {
            item.setSalePrice(dto.getPricing().getSalePrice());
            item.setSalePriceType(dto.getPricing().getSalePriceType());
            item.setDiscountAmount(dto.getPricing().getDiscountAmount());
            item.setDiscountType(dto.getPricing().getDiscountType());
        }

        // Update purchase and tax details
        if (dto.getPurchasePriceTaxes() != null) {
            item.setPurchasePrice(dto.getPurchasePriceTaxes().getPurchasePrice());
            item.setPurchasePriceType(dto.getPurchasePriceTaxes().getPurchasePriceType());
            item.setTaxRateIndex(dto.getPurchasePriceTaxes().getTaxRateIndex());
            if (dto.getPurchasePriceTaxes().getTaxRateId() != null) {
                TaxRateEntity taxRate = taxRateRepository.findById(dto.getPurchasePriceTaxes().getTaxRateId())
                        .orElseThrow(() -> new IllegalArgumentException("Tax rate not found"));
                item.setTaxRate(taxRate);

            }
        }

        // Update stock details
        if (dto.getStock() != null) {
            item.setOpeningQuantity(dto.getStock().getOpeningQuantity());
            item.setAtPrice(dto.getStock().getAtPrice());
            item.setAsOfDate(dto.getStock().getAsOfDate());
            item.setMinStock(dto.getStock().getMinStock());
            item.setLocation(dto.getStock().getLocation());
        }
    }

    private String saveImage(MultipartFile file) {
        try {
            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);

            // Save file
            Files.copy(file.getInputStream(), filePath);

            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Could not save image file: " + e.getMessage());
        }
    }

    private void deleteImage(String imagePath) {
        try {
            Path path = Paths.get(uploadDir).resolve(imagePath);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Log error but don't throw exception as this is cleanup operation
            System.err.println("Error deleting image file: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getAllItems() {
        List<ItemEntity> items = itemRepository.findAll();
        return items.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    private ItemResponseDTO convertToResponseDTO(ItemEntity item) {
        ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setHsn(item.getHsn());
        dto.setUnit(item.getUnit());
        dto.setCategory(item.getCategory());
        dto.setCode(item.getCode());
        dto.setSalePrice(item.getSalePrice());
        dto.setSalePriceType(item.getSalePriceType());
        dto.setDiscountAmount(item.getDiscountAmount());
        dto.setDiscountType(item.getDiscountType());
        dto.setPurchasePrice(item.getPurchasePrice());
        dto.setPurchasePriceType(item.getPurchasePriceType());
        if (item.getTaxRate() != null) {
            dto.setTaxRate(item.getTaxRate());
        }
        dto.setOpeningQuantity(item.getOpeningQuantity());
        dto.setAtPrice(item.getAtPrice());
        dto.setAsOfDate(item.getAsOfDate());
        dto.setMinStock(item.getMinStock());
        dto.setLocation(item.getLocation());
        dto.setImagePath(item.getImagePath());
        return dto;
    }

    @Override
    public List<ItemResponseDTO> updateItemQuantity(Long id, Integer quantity, String transactionType) {
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + id));

        // Update quantity based on transaction type
        if (String.valueOf(TransactionType.PURCHASE).equalsIgnoreCase(transactionType)) {
            item.setOpeningQuantity(item.getOpeningQuantity() + quantity);
        } else if (String.valueOf(TransactionType.SALE).equalsIgnoreCase(transactionType)) {
            int newQuantity = item.getOpeningQuantity() - quantity;
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Insufficient stock to subtract the requested quantity");
            }
            item.setOpeningQuantity(newQuantity);
        } else {
            throw new IllegalArgumentException("Invalid transaction type. Use 'SALE' or 'PURCHASE'.");
        }

        itemRepository.save(item);

        // Return updated item as a list for consistency
        return List.of(convertToResponseDTO(item));
    }
}