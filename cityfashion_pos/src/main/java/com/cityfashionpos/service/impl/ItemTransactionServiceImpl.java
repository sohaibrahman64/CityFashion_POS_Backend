package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.ItemTransactionDTO;
import com.cityfashionpos.dto.ProductTransactionDTO;
import com.cityfashionpos.entity.ItemEntity;
import com.cityfashionpos.entity.ItemTransactionEntity;
import com.cityfashionpos.entity.ProductTransactionEntity;
import com.cityfashionpos.model.TransactionType;
import com.cityfashionpos.repository.ItemRepository;
import com.cityfashionpos.repository.ItemTransactionRepository;
import com.cityfashionpos.service.ItemTransactionService;

@Service
public class ItemTransactionServiceImpl implements ItemTransactionService {

    @Autowired
    private ItemTransactionRepository itemTransactionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ItemTransactionDTO createTransaction(ItemTransactionDTO transactionDTO) {
        ItemEntity item = itemRepository.findById(transactionDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ItemTransactionEntity entity = new ItemTransactionEntity();
        entity.setItem(item);

        // Handle transaction type - ensure it's valid
        TransactionType transactionType = transactionDTO.getTransactionType();
        if (transactionType == null) {
            throw new RuntimeException("Transaction type is required");
        }
        entity.setTransactionType(transactionType);

        entity.setReferenceId(transactionDTO.getReferenceId());
        entity.setReferenceType(transactionDTO.getReferenceType());
        entity.setReferenceNumber(transactionDTO.getReferenceNumber());

        // Handle quantity and unit price conversion
        if (transactionDTO.getQuantity() == null) {
            throw new RuntimeException("Quantity is required");
        }
        entity.setQuantity(transactionDTO.getQuantity());

        if (transactionDTO.getUnitPrice() == null) {
            throw new RuntimeException("Unit price is required");
        }
        entity.setUnitPrice(transactionDTO.getUnitPrice());

        entity.setDescription(transactionDTO.getDescription());

        // Handle transaction date - use provided date or current time
        LocalDateTime transactionDate = transactionDTO.getTransactionDate();
        if (transactionDate == null) {
            transactionDate = LocalDateTime.now();
        }
        entity.setTransactionDate(transactionDate);

        // Handle created date - use provided date or current time
        LocalDateTime createdAt = transactionDTO.getCreatedAt();
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        entity.setCreatedAt(createdAt);

        entity.setCreatedBy(transactionDTO.getCreatedBy() != null ? transactionDTO.getCreatedBy() : "SYSTEM");
        entity.setNotes(transactionDTO.getNotes());
        entity.setStatus(transactionDTO.getStatus() != null ? transactionDTO.getStatus() : "COMPLETED");
        entity.setPartyId(transactionDTO.getPartyId());
        entity.setPartyName(transactionDTO.getPartyName());

        entity.calculateTotalValue();

        ItemTransactionEntity savedEntity = itemTransactionRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    @Override
    public List<ItemTransactionDTO> createTransactions(List<ItemTransactionDTO> transactionDTOs) {
        return transactionDTOs.stream()
                .map(this::createTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemTransactionDTO> getTransactionsByItemId(Long itemId) {
        List<ItemTransactionEntity> entities = itemTransactionRepository.findByItemIdOrderByTransactionDateDesc(itemId);
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemTransactionDTO> getTransactionsByProductAndDateRange(Long itemId, LocalDateTime startDate,
            LocalDateTime endDate) {
        List<ItemTransactionEntity> entities = itemTransactionRepository.findByItemIdAndDateRange(itemId,
                startDate, endDate);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemTransactionDTO> getTransactionsByType(TransactionType transactionType) {
        List<ItemTransactionEntity> entities = itemTransactionRepository
                .findByTransactionTypeOrderByTransactionDateDesc(transactionType);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemTransactionDTO> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<ItemTransactionEntity> entities = itemTransactionRepository.findByDateRange(startDate, endDate);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ItemTransactionDTO getTransactionById(Long transactionId) {
        ItemTransactionEntity entity = itemTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return convertToDTO(entity);
    }

    @Override
    public ItemTransactionDTO updateTransaction(Long transactionId, ItemTransactionDTO transactionDTO) {
        ItemTransactionEntity entity = itemTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // Update fields
        if (transactionDTO.getTransactionType() != null) {
            entity.setTransactionType(transactionDTO.getTransactionType());
        }
        if (transactionDTO.getQuantity() != null) {
            entity.setQuantity(BigDecimal.valueOf(transactionDTO.getQuantity().doubleValue()));
        }
        if (transactionDTO.getUnitPrice() != null) {
            entity.setUnitPrice(BigDecimal.valueOf(transactionDTO.getUnitPrice().doubleValue()));
        }
        if (transactionDTO.getDescription() != null) {
            entity.setDescription(transactionDTO.getDescription());
        }
        if (transactionDTO.getNotes() != null) {
            entity.setNotes(transactionDTO.getNotes());
        }
        if (transactionDTO.getStatus() != null) {
            entity.setStatus(transactionDTO.getStatus());
        }

        entity.calculateTotalValue();

        ItemTransactionEntity updatedEntity = itemTransactionRepository.save(entity);
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        if (!itemTransactionRepository.existsById(transactionId)) {
            throw new RuntimeException("Transaction not found");
        }
        itemTransactionRepository.deleteById(transactionId);
    }

    @Override
    public Object getTransactionSummary(Long itemId, LocalDateTime startDate, LocalDateTime endDate) {
        return itemTransactionRepository.getItemTransactionSummary(itemId);
    }

    @Override
    public ItemTransactionDTO createFromStockAdjustment(Long itemId, TransactionType type, Double quantity,
            Double unitPrice, String description, Long referenceId, String referenceType, String referenceNumber) {
        ItemTransactionDTO dto = new ItemTransactionDTO();
        dto.setItemId(itemId);
        dto.setTransactionType(type);
        dto.setQuantity(BigDecimal.valueOf(quantity));
        dto.setUnitPrice(BigDecimal.valueOf(unitPrice));
        dto.setDescription(description);
        dto.setReferenceId(referenceId);
        dto.setReferenceType(referenceType);
        dto.setReferenceNumber(referenceNumber);
        dto.setTransactionDate(LocalDateTime.now());
        dto.setStatus("COMPLETED");

        return createTransaction(dto);
    }

    @Override
    public ItemTransactionDTO createFromSale(Long itemId, Double quantity, Double unitPrice, String description,
            Long invoiceId, String invoiceNumber) {
        ItemTransactionDTO dto = new ItemTransactionDTO();
        dto.setItemId(itemId);
        dto.setTransactionType(TransactionType.SALE);
        dto.setQuantity(BigDecimal.valueOf(quantity));
        dto.setUnitPrice(BigDecimal.valueOf(unitPrice));
        dto.setDescription(description);
        dto.setReferenceId(invoiceId);
        dto.setReferenceType("INVOICE");
        dto.setReferenceNumber(invoiceNumber);
        dto.setTransactionDate(LocalDateTime.now());
        dto.setStatus("COMPLETED");

        return createTransaction(dto);
    }

    @Override
    public ItemTransactionDTO createFromPurchase(Long itemId, Double quantity, Double unitPrice, String description,
            Long purchaseId, String purchaseNumber) {
        ItemTransactionDTO dto = new ItemTransactionDTO();
        dto.setItemId(itemId);
        dto.setTransactionType(TransactionType.PURCHASE);
        dto.setQuantity(BigDecimal.valueOf(quantity));
        dto.setUnitPrice(BigDecimal.valueOf(unitPrice));
        dto.setDescription(description);
        dto.setReferenceId(purchaseId);
        dto.setReferenceType("PURCHASE");
        dto.setReferenceNumber(purchaseNumber);
        dto.setTransactionDate(LocalDateTime.now());
        dto.setStatus("COMPLETED");

        return createTransaction(dto);
    }

    // Helper method to convert Entity to DTO
    private ItemTransactionDTO convertToDTO(ItemTransactionEntity entity) {
        ItemTransactionDTO dto = new ItemTransactionDTO();
        dto.setId(entity.getId());
        dto.setItemId(entity.getItem().getId());
        dto.setItemName(entity.getItem().getName());
        dto.setTransactionType(entity.getTransactionType());
        dto.setReferenceId(entity.getReferenceId());
        dto.setReferenceType(entity.getReferenceType());
        dto.setReferenceNumber(entity.getReferenceNumber());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalValue(entity.getTotalAmount());
        dto.setDescription(entity.getDescription());
        dto.setTransactionDate(entity.getTransactionDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setNotes(entity.getNotes());
        dto.setStatus(entity.getStatus());
        dto.setPartyId(entity.getPartyId());
        dto.setPartyName(entity.getPartyName());

        return dto;
    }

}
