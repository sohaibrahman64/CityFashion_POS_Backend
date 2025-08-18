package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.StockAdjustmentRequest;
import com.cityfashionpos.entity.ProductEntityNew;
import com.cityfashionpos.entity.StockAdjustmentsEntity;
import com.cityfashionpos.model.AdjustmentType;
import com.cityfashionpos.model.TransactionType;
import com.cityfashionpos.repository.ProductRepositoryNew;
import com.cityfashionpos.repository.StockAdjustmentRepository;
import com.cityfashionpos.response.StockAdjustmentResponse;
import com.cityfashionpos.service.ProductTransactionService;
import com.cityfashionpos.service.StockAdjustmentService;

@Service
public class StockAdjustmentServiceImpl implements StockAdjustmentService {

	@Autowired
	private StockAdjustmentRepository stockAdjustmentRepository;

	@Autowired
	private ProductRepositoryNew productRepository;

	@Autowired
	private ProductTransactionService productTransactionService;

	@Override
	public StockAdjustmentResponse createStockAdjustment(StockAdjustmentRequest request) {
		/// Validate product exists
		ProductEntityNew product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + request.getProductId()));

		// Validate adjustment type
		AdjustmentType adjustmentType;
		try {
			adjustmentType = AdjustmentType.valueOf(request.getAdjustmentType());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid adjustment type: " + request.getAdjustmentType());
		}

		// Create stock adjustment entity
		StockAdjustmentsEntity stockAdjustment = new StockAdjustmentsEntity();
		stockAdjustment.setProduct(product);
		stockAdjustment.setAdjustmentType(adjustmentType);
		stockAdjustment.setQuantity(request.getQuantity());
		stockAdjustment.setAtPrice(request.getAtPrice());
		stockAdjustment.setDescription(request.getDescription());
		stockAdjustment.setAdjustmentDate(request.getAdjustmentDate().atStartOfDay());
		stockAdjustment.setCreatedBy(request.getCreatedBy());
		stockAdjustment.calculateTotalValue();

		// Save the adjustment
		StockAdjustmentsEntity savedAdjustment = stockAdjustmentRepository.save(stockAdjustment);

		// Update product stock
		updateProductStock(product, adjustmentType, request.getQuantity());

		// Create product transaction record
		try {
			TransactionType transactionType = adjustmentType == AdjustmentType.ADD_STOCK
					? TransactionType.STOCK_ADJUSTMENT
					: TransactionType.STOCK_ADJUSTMENT;

			productTransactionService.createFromStockAdjustment(
					product.getId(),
					transactionType,
					request.getQuantity().doubleValue(),
					request.getAtPrice().doubleValue(),
					request.getDescription(),
					savedAdjustment.getId(),
					"STOCK_ADJUSTMENT",
					"ADJ-" + savedAdjustment.getId());
		} catch (Exception e) {
			// Log the error but don't fail the stock adjustment
			System.err.println("Failed to create product transaction: " + e.getMessage());
		}

		// Create response
		return createResponse(savedAdjustment, product);
	}

	/**
	 * Update product stock based on adjustment
	 */
	private void updateProductStock(ProductEntityNew product, AdjustmentType adjustmentType, Integer quantity) {
		Integer currentStock = product.getOpeningQuantity();
		Integer newStock;

		if (adjustmentType == AdjustmentType.ADD_STOCK) {
			newStock = currentStock + quantity;
		} else {
			// REDUCE_STOCK
			newStock = currentStock - quantity;
			if (newStock < 0) {
				throw new RuntimeException("Insufficient stock. Cannot reduce stock below 0.");
			}
		}

		product.setOpeningQuantity(newStock);
		productRepository.save(product);
	}

	@Override
	public List<StockAdjustmentResponse> getAdjustmentsByProduct(Long productId) {
		List<StockAdjustmentsEntity> adjustments = stockAdjustmentRepository
				.findByProductIdOrderByAdjustmentDateDesc(productId);
		ProductEntityNew product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

		return adjustments.stream().map(adjustment -> createResponse(adjustment, product)).collect(Collectors.toList());
	}

	@Override
	public List<StockAdjustmentResponse> getAdjustmentsByDateRange(LocalDate startDate, LocalDate endDate) {
		LocalDateTime startDateTime = startDate.atStartOfDay();
		LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

		List<StockAdjustmentsEntity> adjustments = stockAdjustmentRepository.findByDateRange(startDateTime,
				endDateTime);

		return adjustments.stream().map(adjustment -> createResponse(adjustment, adjustment.getProduct()))
				.collect(Collectors.toList());
	}

	@Override
	public List<StockAdjustmentResponse> getAdjustmentsByType(String adjustmentType) {
		List<StockAdjustmentsEntity> adjustments = stockAdjustmentRepository
				.findByAdjustmentTypeOrderByAdjustmentDateDesc(adjustmentType);

		return adjustments.stream().map(adjustment -> createResponse(adjustment, adjustment.getProduct()))
				.collect(Collectors.toList());
	}

	@Override
	public StockAdjustmentResponse getAdjustmentById(Long id) {
		StockAdjustmentsEntity adjustment = stockAdjustmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock adjustment not found with ID: " + id));

		return createResponse(adjustment, adjustment.getProduct());
	}

	@Override
	public void deleteAdjustment(Long id) {
		StockAdjustmentsEntity adjustment = stockAdjustmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock adjustment not found with ID: " + id));

		ProductEntityNew product = adjustment.getProduct();

		// Reverse the stock adjustment
		AdjustmentType reverseType = adjustment.getAdjustmentType() == AdjustmentType.ADD_STOCK
				? AdjustmentType.REDUCE_STOCK
				: AdjustmentType.ADD_STOCK;

		updateProductStock(product, reverseType, adjustment.getQuantity());

		// Delete the adjustment
		stockAdjustmentRepository.delete(adjustment);

	}

	@Override
	public StockAdjustmentSummary getAdjustmentSummary(Long productId, LocalDate startDate, LocalDate endDate) {
		LocalDateTime startDateTime = startDate.atStartOfDay();
		LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

		Double totalValue = stockAdjustmentRepository.getTotalAdjustmentValueForProduct(productId, startDateTime,
				endDateTime);
		Long addStockCount = stockAdjustmentRepository.countByProductAndAdjustmentType(productId, "ADD_STOCK");
		Long reduceStockCount = stockAdjustmentRepository.countByProductAndAdjustmentType(productId, "REDUCE_STOCK");

		return new StockAdjustmentSummary(productId,
				totalValue != null ? BigDecimal.valueOf(totalValue) : BigDecimal.ZERO, addStockCount, reduceStockCount);
	}

	/**
	 * Create response DTO from entity
	 */
	private StockAdjustmentResponse createResponse(StockAdjustmentsEntity adjustment, ProductEntityNew product) {
		StockAdjustmentResponse response = new StockAdjustmentResponse();
		response.setId(adjustment.getId());
		response.setProductId(product.getId());
		response.setProductName(product.getName());
		response.setAdjustmentType(adjustment.getAdjustmentType().getDisplayName());
		response.setQuantity(adjustment.getQuantity());
		response.setAtPrice(adjustment.getAtPrice());
		response.setDescription(adjustment.getDescription());
		response.setAdjustmentDate(adjustment.getAdjustmentDate().toLocalDate().toString());
		response.setCreatedAt(adjustment.getCreatedAt());
		response.setCreatedBy(adjustment.getCreatedBy());
		response.setTotalValue(adjustment.getTotalValue());
		response.setNewStockQuantity(product.getOpeningQuantity());

		return response;
	}

	/**
	 * Summary class for adjustment statistics
	 */
	public static class StockAdjustmentSummary {
		private Long productId;
		private BigDecimal totalValue;
		private Long addStockCount;
		private Long reduceStockCount;

		public StockAdjustmentSummary(Long productId, BigDecimal totalValue, Long addStockCount,
				Long reduceStockCount) {
			this.productId = productId;
			this.totalValue = totalValue;
			this.addStockCount = addStockCount;
			this.reduceStockCount = reduceStockCount;
		}

		// Getters and Setters
		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public BigDecimal getTotalValue() {
			return totalValue;
		}

		public void setTotalValue(BigDecimal totalValue) {
			this.totalValue = totalValue;
		}

		public Long getAddStockCount() {
			return addStockCount;
		}

		public void setAddStockCount(Long addStockCount) {
			this.addStockCount = addStockCount;
		}

		public Long getReduceStockCount() {
			return reduceStockCount;
		}

		public void setReduceStockCount(Long reduceStockCount) {
			this.reduceStockCount = reduceStockCount;
		}
	}

}
