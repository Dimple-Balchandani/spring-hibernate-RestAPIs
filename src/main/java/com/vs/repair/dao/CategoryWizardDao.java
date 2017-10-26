package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.CategoryWizardEntity;

public interface CategoryWizardDao {

	String QUERY_ALL_CATEGORY_WIZARD = "SELECT c from CategoryWizardEntity c";
	String QUERY_FIND_CATEGORY_WIZARD_BY_ID = "SELECT c from CategoryEntity c WHERE c.id LIKE :id";

	boolean save(CategoryWizardEntity request);
	
	CategoryWizardEntity findById(long id);
	
	void deleteCategoryWizard(CategoryWizardEntity entity);
	
	List<CategoryWizardEntity> listCategoryWizards();
	
	void updateCategoryWizard(long id, CategoryWizardEntity request);
}
