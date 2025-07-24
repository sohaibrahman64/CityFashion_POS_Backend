package com.cityfashionpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.entity.CategoryEntity;
import com.cityfashionpos.entity.ProductCategoryEntity;
import com.cityfashionpos.repository.ProductCategoryRepository;
import com.cityfashionpos.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ProductCategoryRepository categoryRepository;
	

	@Override
	public List<ProductCategoryEntity> getActiveCategories() {
		return categoryRepository.findByActiveTrue();
	}

	@Override
	public ProductCategoryEntity addCategory(String name) {
		ProductCategoryEntity category = new ProductCategoryEntity();
        category.setName(name);
        return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);

	}

}
