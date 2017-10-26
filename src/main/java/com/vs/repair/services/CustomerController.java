package com.vs.repair.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vs.repair.dao.CustomerDao;
import com.vs.repair.model.CustomerEntity;
import com.vs.repair.model.ResponseModel;


@RestController
@Transactional
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class.getName());
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addCustomer(@RequestBody CustomerEntity request){
		LOGGER.debug("Received request to add a Customer.");
		if(customerDao.addCustomer(request))
			return new ResponseModel(HttpStatus.CREATED.value(), "Customer added");
		else
			return new ResponseModel(HttpStatus.FORBIDDEN.value(), "Customer not added");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<CustomerEntity> listCustomer() {
		LOGGER.debug("Received request to get list of Customer.");
		return customerDao.list();
    }
	
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public CustomerEntity getCustomerById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a customer by id.");		
		return customerDao.findById(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteCustomer(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a Customer.");
		CustomerEntity entity = customerDao.findById(id);
		if(entity != null){
			customerDao.deleteCustomer(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Customer deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Customer not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateCustomer(@RequestBody CustomerEntity entity, @RequestParam("id") long id){
		LOGGER.debug("Received request to update a Customer.");
		CustomerEntity customerEntity = customerDao.findById(id);
		if(customerEntity != null){
			customerDao.updateCustomer(id, entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Customer updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Customer not found");			
		}
	}
}
