package com.vs.repair.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.OrderEntity;
import com.vs.repair.model.OrderStatusEntity;
import com.vs.repair.model.StatusEntity;


public class OrderStatusRequest extends OrderStatusEntity{

	private long orderId;
	
	private long statusId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	
	@JsonIgnore
	@Override
	public long getCreatedOn() {
		return super.getCreatedOn();
	}
	
	@Override
	@JsonIgnore
	public StatusEntity getStatus() {
		return super.getStatus();
	}

	@Override
	@JsonIgnore
	public OrderEntity getOrder() {
		return super.getOrder();
	}
}
