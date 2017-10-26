package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.ItemEntity;

@Repository
public class ItemDaoImpl extends AbstractDao<Long, ItemEntity> implements ItemDao{
	
	public boolean addItem(ItemEntity item) {
		try {
			persist(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteItem(ItemEntity item) {
		delete(item);
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemEntity> list() {
		return entityManager.createQuery("SELECT u from ItemEntity u").getResultList();
	}

	public void updateItem(long id, ItemEntity item) {
		item.setId(id);
		update(item);
	}
	public ItemEntity findById(long id) {
		ItemEntity entity = getByKey(id);
		return entity;
	}
}
