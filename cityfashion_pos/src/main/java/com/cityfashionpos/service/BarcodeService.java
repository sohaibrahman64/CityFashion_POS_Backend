package com.cityfashionpos.service;

import com.cityfashionpos.entity.BarcodeRequest;
import com.cityfashionpos.response.BarcodeResponse;

public interface BarcodeService {
	String generateNextBarcode();
	BarcodeResponse saveBarcodes(BarcodeRequest request);
	BarcodeResponse getAllBarcodes();
	BarcodeResponse getBarcodeById(Long id);
	BarcodeResponse getBarcodeByProductCode(String productCode);
	BarcodeResponse deleteBarcodeById(Long id);
	BarcodeResponse updateBarcode(Long id, BarcodeRequest.BarcodeData barcodeData);
}
