package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.CategoryWizardEntity;

@Repository("categoryWizardDao")
public class CategoryWizardDaoImpl extends AbstractDao<Long, CategoryWizardEntity> implements CategoryWizardDao {

	@Override
	public boolean save(CategoryWizardEntity entity) {
		try {
			persist(entity);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public CategoryWizardEntity findById(long id) {
		CategoryWizardEntity entity = getByKey(id);
		return entity;
	}

	@Override
	public void deleteCategoryWizard(CategoryWizardEntity entity) {
		super.delete(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryWizardEntity> listCategoryWizards() {
		List<CategoryWizardEntity> categoryWizardEntitie = getEntityManager().createQuery(QUERY_ALL_CATEGORY_WIZARD)
				.getResultList();
		return categoryWizardEntitie;
	}

	@Override
	public void updateCategoryWizard(long id, CategoryWizardEntity categoryWizard) {
		categoryWizard.setId(id);
		update(categoryWizard);
	}
}
