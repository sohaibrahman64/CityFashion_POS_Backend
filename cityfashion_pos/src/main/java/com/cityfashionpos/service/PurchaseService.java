package com.cityfashionpos.service;

import java.util.List;

import com.cityfashionpos.dto.PurchaseDTO;
import com.cityfashionpos.entity.PurchaseEntity;

public interface PurchaseService {
	public PurchaseEntity createPurchase(PurchaseDTO dto);
	public List<PurchaseDTO> getAllPurchases();
}
