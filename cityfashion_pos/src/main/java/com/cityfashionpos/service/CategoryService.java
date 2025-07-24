package com.cityfashionpos.service;

import java.util.List;

import com.cityfashionpos.entity.CategoryEntity;
import com.cityfashionpos.entity.ProductCategoryEntity;

public interface CategoryService {
	List<ProductCategoryEntity> getActiveCategories();
	ProductCategoryEntity addCategory(String name);
	void deleteCategory(Long id);
}
