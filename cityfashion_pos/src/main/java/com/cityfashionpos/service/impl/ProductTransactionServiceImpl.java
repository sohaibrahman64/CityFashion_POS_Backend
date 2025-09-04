package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.ProductTransactionDTO;
import com.cityfashionpos.entity.ProductEntityNew;
import com.cityfashionpos.entity.ProductTransactionEntity;
import com.cityfashionpos.model.TransactionType;
import com.cityfashionpos.repository.ProductRepositoryNew;
import com.cityfashionpos.repository.ProductTransactionRepository;
import com.cityfashionpos.service.ProductTransactionService;

@Service
public class ProductTransactionServiceImpl implements ProductTransactionService {

    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    @Autowired
    private ProductRepositoryNew productRepository;

    @Override
    public ProductTransactionDTO createTransaction(ProductTransactionDTO transactionDTO) {
        ProductEntityNew product = productRepository.findById(transactionDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductTransactionEntity entity = new ProductTransactionEntity();
        entity.setProduct(product);

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

        entity.calculateTotalValue();

        ProductTransactionEntity savedEntity = productTransactionRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    @Override
    public List<ProductTransactionDTO> createTransactions(List<ProductTransactionDTO> transactionDTOs) {
        return transactionDTOs.stream()
                .map(this::createTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductTransactionDTO> getTransactionsByProduct(Long productId) {
        List<ProductTransactionEntity> entities = productTransactionRepository
                .findByProductIdOrderByTransactionDateDesc(productId);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductTransactionDTO> getTransactionsByProductAndDateRange(Long productId, LocalDateTime startDate,
            LocalDateTime endDate) {
        List<ProductTransactionEntity> entities = productTransactionRepository.findByProductIdAndDateRange(productId,
                startDate, endDate);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductTransactionDTO> getTransactionsByType(TransactionType transactionType) {
        List<ProductTransactionEntity> entities = productTransactionRepository
                .findByTransactionTypeOrderByTransactionDateDesc(transactionType);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductTransactionDTO> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<ProductTransactionEntity> entities = productTransactionRepository.findByDateRange(startDate, endDate);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductTransactionDTO getTransactionById(Long transactionId) {
        ProductTransactionEntity entity = productTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return convertToDTO(entity);
    }

    @Override
    public ProductTransactionDTO updateTransaction(Long transactionId, ProductTransactionDTO transactionDTO) {
        ProductTransactionEntity entity = productTransactionRepository.findById(transactionId)
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

        ProductTransactionEntity updatedEntity = productTransactionRepository.save(entity);
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        if (!productTransactionRepository.existsById(transactionId)) {
            throw new RuntimeException("Transaction not found");
        }
        productTransactionRepository.deleteById(transactionId);
    }

    @Override
    public Object getTransactionSummary(Long productId, LocalDateTime startDate, LocalDateTime endDate) {
        return productTransactionRepository.getProductTransactionSummary(productId);
    }

    @Override
    public ProductTransactionDTO createFromStockAdjustment(Long productId, TransactionType type,
            Double quantity, Double unitPrice, String description,
            Long referenceId, String referenceType, String referenceNumber) {
        ProductTransactionDTO dto = new ProductTransactionDTO();
        dto.setProductId(productId);
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
    public ProductTransactionDTO createFromSale(Long productId, Double quantity, Double unitPrice,
            String description, Long invoiceId, String invoiceNumber) {
        ProductTransactionDTO dto = new ProductTransactionDTO();
        dto.setProductId(productId);
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
    public ProductTransactionDTO createFromPurchase(Long productId, Double quantity, Double unitPrice,
            String description, Long purchaseId, String purchaseNumber) {
        ProductTransactionDTO dto = new ProductTransactionDTO();
        dto.setProductId(productId);
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
    private ProductTransactionDTO convertToDTO(ProductTransactionEntity entity) {
        ProductTransactionDTO dto = new ProductTransactionDTO();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProduct().getId());
        dto.setProductName(entity.getProduct().getName());
        dto.setTransactionType(entity.getTransactionType());
        dto.setReferenceId(entity.getReferenceId());
        dto.setReferenceType(entity.getReferenceType());
        dto.setReferenceNumber(entity.getReferenceNumber());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalValue(entity.getTotalValue());
        dto.setDescription(entity.getDescription());
        dto.setTransactionDate(entity.getTransactionDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setNotes(entity.getNotes());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
