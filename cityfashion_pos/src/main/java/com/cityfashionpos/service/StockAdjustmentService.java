package com.cityfashionpos.service;

import java.time.LocalDate;
import java.util.List;

import com.cityfashionpos.dto.StockAdjustmentRequest;
import com.cityfashionpos.response.StockAdjustmentResponse;
import com.cityfashionpos.service.impl.StockAdjustmentServiceImpl.StockAdjustmentSummary;

public interface StockAdjustmentService {
	public StockAdjustmentResponse createStockAdjustment(StockAdjustmentRequest request);
	public List<StockAdjustmentResponse> getAdjustmentsByProduct(Long productId);
	public List<StockAdjustmentResponse> getAdjustmentsByDateRange(LocalDate startDate, LocalDate endDate);
	public List<StockAdjustmentResponse> getAdjustmentsByType(String adjustmentType);
	public StockAdjustmentResponse getAdjustmentById(Long id);
	public void deleteAdjustment(Long id);
	public StockAdjustmentSummary getAdjustmentSummary(Long productId, LocalDate startDate, LocalDate endDate);
}
