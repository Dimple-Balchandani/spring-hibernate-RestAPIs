package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.OrderEntity;

@Repository
public class OrderDaoImpl extends AbstractDao<Long, OrderEntity> implements OrderDao {

	public boolean addOrder(OrderEntity privilege) {
		try {
			persist(privilege);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteOrder(OrderEntity privilege) {
		delete(privilege);
	}

	@SuppressWarnings("unchecked")
	public List<OrderEntity> list() {
		return entityManager.createQuery("SELECT u from OrderEntity u").getResultList();
	}

	public void updateOrder(long id, OrderEntity order) {
		order.setId(id);
		update(order);
	}

	public OrderEntity findById(long id) {
		OrderEntity entity = getByKey(id);
		return entity;

	}
}
