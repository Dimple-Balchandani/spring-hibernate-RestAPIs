package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.OrderStatusEntity;

@Repository
public class OrderStatusDaoImpl extends AbstractDao<Long, OrderStatusEntity> implements OrderStatusDao {

	public boolean addOrderStatus(OrderStatusEntity status) {
		try {
			persist(status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteOrderStatus(OrderStatusEntity status) {
		delete(status);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderStatusEntity> list() {
		return entityManager.createQuery("SELECT u from OrderStatusEntity u").getResultList();
	}

	@Override
	public void updateOrderStatus(long id, OrderStatusEntity status) {
		status.setId(id);
		update(status);
	}

	public OrderStatusEntity findById(long id) {
		OrderStatusEntity entity = getByKey(id);
		return entity;
	}
}
