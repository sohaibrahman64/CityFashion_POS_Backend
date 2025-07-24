package com.cityfashionpos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.repository.ProductRepository;
import com.cityfashionpos.service.BarcodeService;
import com.cityfashionpos.utils.BarcodeUtil;

@Service
public class BarcodeServiceImpl implements BarcodeService {
	
	@Autowired
    private ProductRepository productRepository;

	@Override
	public String generateNextBarcode() {
        Long maxId = productRepository.findMaxProductId();
        Long nextId = (maxId == null) ? 1L : maxId + 1;
        String newBarcode = BarcodeUtil.generateFromId(nextId);

        // Double-check uniqueness
        while (productRepository.existsByBarcode(newBarcode)) {
            nextId++;
            newBarcode = BarcodeUtil.generateFromId(nextId);
        }

        return newBarcode;
	}	
	
	
	

}
