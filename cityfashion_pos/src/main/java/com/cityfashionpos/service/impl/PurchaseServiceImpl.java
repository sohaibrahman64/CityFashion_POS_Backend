package com.cityfashionpos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.PurchaseDTO;
import com.cityfashionpos.dto.PurchaseItemDTO;
import com.cityfashionpos.entity.ProductEntity;
import com.cityfashionpos.entity.PurchaseEntity;
import com.cityfashionpos.entity.PurchaseItemEntity;
import com.cityfashionpos.repository.PaymentTypesRepository;
import com.cityfashionpos.repository.ProductRepository;
import com.cityfashionpos.repository.PurchaseRepository;
import com.cityfashionpos.repository.SupplierRepository;
import com.cityfashionpos.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PaymentTypesRepository paymentModeRepository;

	@Override
	public PurchaseEntity createPurchase(PurchaseDTO dto) {
		PurchaseEntity purchase = new PurchaseEntity();
		purchase.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElseThrow());
		purchase.setBillNumber(dto.getBillNumber());
		purchase.setPurchaseDate(dto.getPurchaseDate());
		purchase.setPaymentMode(paymentModeRepository.findById(dto.getPaymentModeId()).orElseThrow());
		purchase.setDescription(dto.getDescription());
		purchase.setImagePath(dto.getImagePath());
		purchase.setTotalAmount(dto.getTotalAmount());

		List<PurchaseItemEntity> items = new ArrayList<>();
		for (PurchaseItemDTO item : dto.getItems()) {
			ProductEntity product = productRepository.findById(item.getProductId()).orElseThrow();
			PurchaseItemEntity purchaseItem = new PurchaseItemEntity();
			purchaseItem.setPurchase(purchase);
			purchaseItem.setProduct(product);
			purchaseItem.setQuantity(item.getQuantity());
			purchaseItem.setPricePerUnit(item.getPricePerUnit());
			purchaseItem.setDiscountPercent(item.getDiscountPercent());
			purchaseItem.setTaxPercent(item.getTaxPercent());
			purchaseItem.setTotalPrice(item.getTotalPrice());

			items.add(purchaseItem);
		}

		purchase.setItems(items);
		return purchaseRepository.save(purchase);
	}

	// Mapping PurchaseEntity to PurchaseDTO
	@Override
	public List<PurchaseDTO> getAllPurchases() {
		List<PurchaseEntity> purchases = purchaseRepository.findAllWithItems();
		return purchases.stream().map(this::toDTO).collect(Collectors.toList());
	}

	private PurchaseDTO toDTO(PurchaseEntity entity) {
		PurchaseDTO dto = new PurchaseDTO();
		dto.setId(entity.getId());
		dto.setSupplierName(entity.getSupplier() != null ? entity.getSupplier().getName() : "");
		dto.setBillNumber(entity.getBillNumber());
		dto.setPurchaseDate(entity.getPurchaseDate());
		dto.setPaymentMode(entity.getPaymentMode() != null ? entity.getPaymentMode().getPaymentType() : "");
		dto.setDescription(entity.getDescription());
		dto.setImagePath(entity.getImagePath());
		dto.setTotalAmount(entity.getTotalAmount());
		List<PurchaseItemDTO> itemDTOs = entity.getItems().stream().map(this::itemToDTO).collect(Collectors.toList());
		dto.setItems(itemDTOs);
		return dto;
	}

	private PurchaseItemDTO itemToDTO(PurchaseItemEntity item) {
		PurchaseItemDTO dto = new PurchaseItemDTO();
		dto.setId(item.getId());
		dto.setProductName(item.getProduct() != null ? item.getProduct().getName() : "");
		dto.setQuantity(item.getQuantity());
		dto.setPricePerUnit(item.getPricePerUnit());
		dto.setDiscountPercent(item.getDiscountPercent());
		dto.setTaxPercent(item.getTaxPercent());
		dto.setTotalPrice(item.getTotalPrice());
		return dto;
	}

}
