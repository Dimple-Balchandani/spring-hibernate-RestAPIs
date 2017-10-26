package com.vs.repair.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.CategoryEntity;
import com.vs.repair.model.CustomerEntity;
import com.vs.repair.model.ItemEntity;

public class ItemRequest extends ItemEntity {
	
	private long categoryId;
	
	private long customerId;
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	@JsonIgnore
	@Override
	public long getCreatedOn() {
		// TODO Auto-generated method stub
		return super.getCreatedOn();
	}

	@JsonIgnore
	@Override
	public CategoryEntity getCategory() {
		// TODO Auto-generated method stub
		return super.getCategory();
	}
	
	@JsonIgnore
	@Override
	public CustomerEntity getCustomer() {
		// TODO Auto-generated method stub
		return super.getCustomer();
	}


}
