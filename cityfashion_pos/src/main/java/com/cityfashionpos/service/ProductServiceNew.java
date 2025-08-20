package com.cityfashionpos.service;

import java.util.List;

import com.cityfashionpos.dto.ProductRequestDTO;
import com.cityfashionpos.dto.ProductResponseDTO;

public interface ProductServiceNew {
	public ProductResponseDTO saveProduct(ProductRequestDTO requestDTO);

	public ProductResponseDTO getProductById(Long id);

	public ProductResponseDTO getProductByCode(String code);

	public List<ProductResponseDTO> getAllProducts();

	public List<ProductResponseDTO> searchProductsByName(String name);

	public List<ProductResponseDTO> searchProductsByNameStartingWith(String name);

	public List<ProductResponseDTO> getProductsByCategory(String category);

	public List<ProductResponseDTO> getProductsWithLowStock();

	public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO);

	public void deleteProduct(Long id);

	public String generateProductCode();

}
