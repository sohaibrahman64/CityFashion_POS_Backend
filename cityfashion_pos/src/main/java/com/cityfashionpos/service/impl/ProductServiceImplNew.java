package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.ProductRequestDTO;
import com.cityfashionpos.dto.ProductResponseDTO;
import com.cityfashionpos.entity.ProductEntityNew;
import com.cityfashionpos.model.DiscountType;
import com.cityfashionpos.model.PriceType;
import com.cityfashionpos.model.TaxType;
import com.cityfashionpos.repository.ProductRepositoryNew;
import com.cityfashionpos.service.ProductServiceNew;

@Service
@Transactional
public class ProductServiceImplNew implements ProductServiceNew {

	@Autowired
	private ProductRepositoryNew productRepository;

	/**
	 * Save a new product
	 */
	@Override
	public ProductResponseDTO saveProduct(ProductRequestDTO requestDTO) {
		// Validate required fields
		if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Product name is required");
		}

		if (requestDTO.getCode() == null || requestDTO.getCode().trim().isEmpty()) {
			throw new IllegalArgumentException("Product code is required");
		}

		// Check if product code already exists
		if (productRepository.existsByCode(requestDTO.getCode())) {
			throw new IllegalArgumentException("Product code already exists: " + requestDTO.getCode());
		}

		// Create new product entity
		ProductEntityNew product = new ProductEntityNew();

		// Set basic information
		product.setName(requestDTO.getName().trim());
		product.setHsn(requestDTO.getHsn());
		product.setCategory(requestDTO.getCategory());
		product.setCode(requestDTO.getCode().trim());

		// Set unit information
		if (requestDTO.getUnit() != null) {
			product.setUnitValue(requestDTO.getUnit().getValue());
			product.setUnitLabel(requestDTO.getUnit().getLabel());
		}

		// Set image URL
		if (requestDTO.getImageUrl() != null && !requestDTO.getImageUrl().trim().isEmpty()) {
			product.setImageUrl(requestDTO.getImageUrl());
		}

		// Set pricing information
		if (requestDTO.getPricing() != null) {
			product.setSalePrice(requestDTO.getPricing().getSalePrice() != null ? requestDTO.getPricing().getSalePrice()
					: BigDecimal.ZERO);

			if (requestDTO.getPricing().getSalePriceType() != null) {
				try {
					product.setSalePriceType(PriceType
							.valueOf(requestDTO.getPricing().getSalePriceType().toUpperCase()));
				} catch (IllegalArgumentException e) {
					product.setSalePriceType(PriceType.WITHOUT_TAX);
				}
			}

			product.setDiscountAmount(
					requestDTO.getPricing().getDiscountAmount() != null ? requestDTO.getPricing().getDiscountAmount()
							: BigDecimal.ZERO);

			if (requestDTO.getPricing().getDiscountType() != null) {
				try {
					product.setDiscountType(DiscountType
							.valueOf(requestDTO.getPricing().getDiscountType().toUpperCase()));
				} catch (IllegalArgumentException e) {
					product.setDiscountType(DiscountType.PERCENTAGE);
				}
			}
		}

		// Set stock information
		if (requestDTO.getStock() != null) {
			product.setOpeningQuantity(
					requestDTO.getStock().getOpeningQuantity() != null ? requestDTO.getStock().getOpeningQuantity()
							: 0);

			product.setAtPrice(
					requestDTO.getStock().getAtPrice() != null ? requestDTO.getStock().getAtPrice() : BigDecimal.ZERO);

			product.setAsOfDate(requestDTO.getStock().getAsOfDate() != null ? requestDTO.getStock().getAsOfDate()
					: LocalDateTime.now());

			product.setMinStock(requestDTO.getStock().getMinStock() != null ? requestDTO.getStock().getMinStock() : 0);
		}
		
		// Set purchase price and taxes
		if (requestDTO.getPurchasePriceTaxes() != null) {
			product.setPurchasePrice(requestDTO.getPurchasePriceTaxes().getPurchasePrice() != null ? requestDTO.getPurchasePriceTaxes().getPurchasePrice() : null);
			if (requestDTO.getPurchasePriceTaxes().getPurchasePriceType() != null) {
				try {
					product.setPurchasePriceType(PriceType.valueOf(requestDTO.getPurchasePriceTaxes().getPurchasePriceType().toUpperCase()));
				} catch (IllegalArgumentException e) {
					product.setPurchasePriceType(PriceType.WITHOUT_TAX);
				}
			}
			//product.setTaxType(TaxType.valueOf(requestDTO.getPurchasePriceTaxes().getTaxType() != null ? requestDTO.getPurchasePriceTaxes().getTaxType() : null));
			if (requestDTO.getPurchasePriceTaxes().getTaxType() != null) {
				try {
					product.setTaxType(TaxType.valueOf(requestDTO.getPurchasePriceTaxes().getTaxType().toUpperCase()));
				} catch (IllegalArgumentException e) {
					product.setTaxType(TaxType.GST_0);
				}
			}
			product.setUpdatedAt(LocalDateTime.now());
		}

		// Set audit fields
		product.setCreatedBy("system"); // You might get this from authentication context
		product.setUpdatedBy("system");

		// Save product
		ProductEntityNew savedProduct = productRepository.save(product);

		// Convert to response DTO
		return convertToResponseDTO(savedProduct);
	}

	/**
	 * Get product by ID
	 */
	@Override
	public ProductResponseDTO getProductById(Long id) {
		Optional<ProductEntityNew> product = productRepository.findById(id);
		if (product.isPresent()) {
			return convertToResponseDTO(product.get());
		}
		throw new IllegalArgumentException("Product not found with ID: " + id);
	}

	/**
	 * Get product by code
	 */
	@Override
	public ProductResponseDTO getProductByCode(String code) {
		Optional<ProductEntityNew> product = productRepository.findByCode(code);
		if (product.isPresent()) {
			return convertToResponseDTO(product.get());
		}
		throw new IllegalArgumentException("Product not found with code: " + code);
	}

	/**
	 * Get all products
	 */
	@Override
	public List<ProductResponseDTO> getAllProducts() {
		List<ProductEntityNew> products = productRepository.findAll();
		return products.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	/**
	 * Search products by name
	 */
	@Override
	public List<ProductResponseDTO> searchProductsByName(String name) {
		List<ProductEntityNew> products = productRepository.findByNameContainingIgnoreCase(name);
		return products.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	/**
	 * Get products by category
	 */
	@Override
	public List<ProductResponseDTO> getProductsByCategory(String category) {
		List<ProductEntityNew> products = productRepository.findByCategory(category);
		return products.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
	}

	/**
	 * Get products with low stock
	 */
	@Override
	public List<ProductResponseDTO> getProductsWithLowStock() {
		List<ProductEntityNew> products = productRepository.findProductsWithLowStock();
		return products.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
	}
	
    /**
     * Update product
     */
	@Override
	public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Optional<ProductEntityNew> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        
        ProductEntityNew product = existingProduct.get();
        
        // Update fields (similar to save but for existing product)
        if (requestDTO.getName() != null) {
            product.setName(requestDTO.getName().trim());
        }
        
        if (requestDTO.getHsn() != null) {
            product.setHsn(requestDTO.getHsn());
        }
        
        if (requestDTO.getCategory() != null) {
            product.setCategory(requestDTO.getCategory());
        }
        
        // Update other fields as needed...
        product.setUpdatedAt(LocalDateTime.now());
        product.setUpdatedBy("system");
        
        ProductEntityNew updatedProduct = productRepository.save(product);
        return convertToResponseDTO(updatedProduct);
	}

	 /**
     * Delete product
     */
	@Override
	public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
	}
	
    /**
     * Generate unique product code
     */
	@Override
	public String generateProductCode() {
        String code;
        do {
            code = generateRandomCode();
        } while (productRepository.existsByCode(code));
        
        return code;
	}
	
    /**
     * Convert Product entity to ProductResponseDTO
     */
    private ProductResponseDTO convertToResponseDTO(ProductEntityNew product) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        
        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setHsn(product.getHsn());
        responseDTO.setCategory(product.getCategory().getName());
        responseDTO.setCode(product.getCode());
        responseDTO.setImageUrl(product.getImageUrl());
        responseDTO.setCreatedAt(product.getCreatedAt());
        responseDTO.setUpdatedAt(product.getUpdatedAt());
        responseDTO.setCreatedBy(product.getCreatedBy());
        responseDTO.setUpdatedBy(product.getUpdatedBy());
        
        // Set unit information
        if (product.getUnitValue() != null || product.getUnitLabel() != null) {
            ProductResponseDTO.UnitDTO unitDTO = new ProductResponseDTO.UnitDTO();
            unitDTO.setValue(product.getUnitValue());
            unitDTO.setLabel(product.getUnitLabel());
            responseDTO.setUnit(unitDTO);
        }
        
        // Set pricing information
        ProductResponseDTO.PricingDTO pricingDTO = new ProductResponseDTO.PricingDTO();
        pricingDTO.setSalePrice(product.getSalePrice());
        pricingDTO.setSalePriceType(product.getSalePriceType() != null ? 
            product.getSalePriceType().name() : null);
        pricingDTO.setDiscountAmount(product.getDiscountAmount());
        pricingDTO.setDiscountType(product.getDiscountType() != null ? 
            product.getDiscountType().name() : null);
        responseDTO.setPricing(pricingDTO);
        
        // Set stock information
        ProductResponseDTO.StockDTO stockDTO = new ProductResponseDTO.StockDTO();
        stockDTO.setOpeningQuantity(product.getOpeningQuantity());
        stockDTO.setAtPrice(product.getAtPrice());
        stockDTO.setAsOfDate(product.getAsOfDate());
        stockDTO.setMinStock(product.getMinStock());
        responseDTO.setStock(stockDTO);
        
        // Set purchase and tax information
        ProductResponseDTO.PurchasePriceTaxesDTO purchasePriceTaxesDTO = new ProductResponseDTO.PurchasePriceTaxesDTO();
        purchasePriceTaxesDTO.setPurchasePrice(product.getPurchasePrice());
        purchasePriceTaxesDTO.setPurchasePriceType(product.getPurchasePriceType() != null ? product.getPurchasePriceType().name() : null);
        purchasePriceTaxesDTO.setTaxType(product.getTaxType() != null ? product.getTaxType().name() : null);
        
        
        return responseDTO;
    }
    
    /**
     * Generate random 11-digit product code
     */
    private String generateRandomCode() {
        long min = 10000000000L;
        long max = 99999999999L;
        long randomCode = min + (long) (Math.random() * (max - min + 1));
        return String.valueOf(randomCode);
    }

}
