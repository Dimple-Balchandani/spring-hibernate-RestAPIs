package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.ItemEntity;

public interface ItemDao {
	
	boolean addItem(ItemEntity request);
	
	void deleteItem(ItemEntity request);
	
	List<ItemEntity> list();
	
	void updateItem(long id, ItemEntity request);

	ItemEntity findById(long id);
}
