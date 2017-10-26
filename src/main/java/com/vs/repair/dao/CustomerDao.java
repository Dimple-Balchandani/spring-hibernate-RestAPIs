package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.CustomerEntity;

public interface CustomerDao {
	
	boolean addCustomer(CustomerEntity request);
	
	void deleteCustomer(CustomerEntity request);
	
	List<CustomerEntity> list();
	
	void updateCustomer(long id, CustomerEntity request);

	CustomerEntity findById(long id);
}
