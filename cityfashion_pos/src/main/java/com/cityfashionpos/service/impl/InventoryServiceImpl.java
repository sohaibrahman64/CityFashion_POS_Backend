package com.cityfashionpos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.InventoryDTO;
import com.cityfashionpos.entity.InventoryEntity;
import com.cityfashionpos.entity.ProductEntity;
import com.cityfashionpos.repository.InventoryRepository;
import com.cityfashionpos.repository.ProductRepository;
import com.cityfashionpos.repository.SupplierRepository;
import com.cityfashionpos.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public InventoryEntity addInventory(InventoryDTO dto) {
		ProductEntity product = productRepository.findById(dto.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found"));

		// Check if inventory already exists for this product
		Optional<InventoryEntity> existingInventoryOpt = inventoryRepository.findByProduct(product);

		InventoryEntity inventory;
		if (existingInventoryOpt.isPresent()) {
			// Update existing inventory's quantity
			inventory = existingInventoryOpt.get();
			inventory.setQuantity(inventory.getQuantity() + dto.getQuantity());
		} else {
			inventory = new InventoryEntity();
			inventory.setProduct(product);
			inventory.setQuantity(dto.getQuantity());
		}

		return inventoryRepository.save(inventory);
	}

	@Override
	public List<InventoryDTO> getInventoryList() {
		return inventoryRepository.getInventoryList();
	}

	@Override
	public Optional<InventoryEntity> findByProductBarcode(String barcode) {
		return inventoryRepository.findByProductBarcode(barcode);
	}

	@Override
	public InventoryEntity updateInventory(Long id, InventoryDTO dto) {
		InventoryEntity inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));

		inventory.setQuantity(dto.getQuantity());

		return inventoryRepository.save(inventory);
	}

}
