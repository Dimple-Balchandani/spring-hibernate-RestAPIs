package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.OrderStatusEntity;

public interface OrderStatusDao {
	
	boolean addOrderStatus(OrderStatusEntity request);
	
	void deleteOrderStatus(OrderStatusEntity request);
	
	List<OrderStatusEntity> list();
	
	void updateOrderStatus(long id, OrderStatusEntity request);
	
	OrderStatusEntity findById(long id);
}
