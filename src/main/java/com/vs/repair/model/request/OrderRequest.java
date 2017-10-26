package com.vs.repair.model.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.ItemEntity;
import com.vs.repair.model.OrderEntity;
import com.vs.repair.model.OrderStatusEntity;
import com.vs.repair.model.UserEntity;

public class OrderRequest extends OrderEntity{

	/**
	 * 
	 */

	private long providerId;
	
	private long shipperId;

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public long getShipperId() {
		return shipperId;
	}

	public void setShipperId(long shipperId) {
		this.shipperId = shipperId;
	}
	
	@JsonIgnore
	@Override
	public long getCreatedOn() {
		return super.getCreatedOn();
	}
	
	@JsonIgnore
	@Override
	public UserEntity getProviderUser() {
		return super.getProviderUser();
	}
		
	@JsonIgnore
	@Override
	public UserEntity getShipperUser() {
		return super.getShipperUser();
	}
	
	@JsonIgnore
	@Override
	public ItemEntity getItem() {
		return super.getItem();
	}
	
	@JsonIgnore
	@Override
	public Set<OrderStatusEntity> getStatus() {
		return super.getStatus();
	}
}
