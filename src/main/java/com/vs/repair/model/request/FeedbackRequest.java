package com.vs.repair.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.CustomerEntity;
import com.vs.repair.model.FeedbackEntity;
import com.vs.repair.model.ItemEntity;

public class FeedbackRequest extends FeedbackEntity{

	private long customerId;
	
	private long itemId;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
	@JsonIgnore
	@Override
	public long getCreatedOn() {
		// TODO Auto-generated method stub
		return super.getCreatedOn();
	}
	
	@JsonIgnore
	@Override
	public ItemEntity getItem() {
		return super.getItem();
	}
	
	@JsonIgnore
	@Override
	public CustomerEntity getCustomer() {
		return super.getCustomer();
	}
}
