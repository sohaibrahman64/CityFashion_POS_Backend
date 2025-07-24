package com.cityfashionpos.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.entity.ProductEntity;

public interface ProductService {
	ProductEntity saveProduct(ProductEntity product);
	
	List<ProductEntity> getAllProducts();
	
	void deleteProduct(Long id);
	
	Optional<ProductEntity> getProductByBarcode(String barcode);
	
	public void importFromExcel(MultipartFile file) throws IOException;

	
}
