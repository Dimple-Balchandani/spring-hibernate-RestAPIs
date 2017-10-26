package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.OrderEntity;

public interface OrderDao {
	boolean addOrder(OrderEntity request);

	void deleteOrder(OrderEntity request);

	List<OrderEntity> list();

	void updateOrder(long id, OrderEntity request);

	OrderEntity findById(long id);
}
