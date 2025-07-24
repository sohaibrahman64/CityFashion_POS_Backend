package com.cityfashionpos.service;

import java.util.List;
import java.util.Optional;

import com.cityfashionpos.dto.InventoryDTO;
import com.cityfashionpos.entity.InventoryEntity;


public interface InventoryService {
	InventoryEntity addInventory(InventoryDTO inventoryDTO);
	List<InventoryDTO> getInventoryList();
	Optional<InventoryEntity> findByProductBarcode(String barcode);
	InventoryEntity updateInventory(Long id, InventoryDTO dto);
}
