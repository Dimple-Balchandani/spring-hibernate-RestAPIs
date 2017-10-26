package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.CustomerEntity;

@Repository
public class CustomerDaoImpl extends AbstractDao<Long, CustomerEntity> implements CustomerDao{
	
	public boolean addCustomer(CustomerEntity customer) {
		try {
			persist(customer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteCustomer(CustomerEntity customer) {
		delete(customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<CustomerEntity> list() {
		return entityManager.createQuery("SELECT u from CustomerEntity u").getResultList();
	}

	public void updateCustomer(long id, CustomerEntity customer) {
		customer.setId(id);
		update(customer);
	}

	public CustomerEntity findById(long id) {
		CustomerEntity entity = getByKey(id);
		return entity;
		
	}
}
