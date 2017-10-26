package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.CategoryEntity;

public interface CategoryDao {
	
	String QUERY_ALL_CATEGORY = "SELECT c from CategoryEntity c ORDER BY c.categoryName";
	String QUERY_FIND_CATEGORY_BY_ID = "SELECT c from CategoryEntity c WHERE c.id LIKE :id";
	String QUERY_FIND_CATEGORY_BY_NAME = "SELECT c from CategoryEntity c WHERE c.categoryName LIKE :categoryName";

	void addCategory(CategoryEntity request);
	
	CategoryEntity findById(long id);
	
	void deleteCategory(CategoryEntity entity);
	
	List<CategoryEntity> listCategory();
	
	void updateCategory(long id, CategoryEntity request);
	
//	CategoryEntity findByName(String name);
}
