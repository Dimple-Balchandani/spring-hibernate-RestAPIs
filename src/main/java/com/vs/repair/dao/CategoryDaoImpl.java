package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.CategoryEntity;

@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Long, CategoryEntity> implements CategoryDao{

	@Override
	public void addCategory(CategoryEntity category) {
		persist(category);
	}

	@Override
	public void deleteCategory(CategoryEntity entity) {
		delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryEntity> listCategory() {
		List<CategoryEntity> categoryList = getEntityManager().createQuery(CategoryDao.QUERY_ALL_CATEGORY).getResultList();
		return categoryList;
	}

	@Override
	public CategoryEntity findById(long id) {
		CategoryEntity entity = getByKey(id);
		return entity;
	}

	@Override
	public void updateCategory(long id, CategoryEntity category) {
		category.setId(id);
		update(category);
	}
	
//	@Override
//	public CategoryEntity findByName(String name) throws NoResultException, NonUniqueResultException {
//		try{
//			CategoryEntity entity = (CategoryEntity) getEntityManager().createQuery(QUERY_FIND_CATEGORY_BY_NAME).setParameter("categoryName", name).getSingleResult();
//			return entity;
//		} catch(NoResultException | NonUniqueResultException e) {
//			throw e;
//		}
//	}

}
