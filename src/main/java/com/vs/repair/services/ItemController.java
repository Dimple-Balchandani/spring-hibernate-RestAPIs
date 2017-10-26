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

import com.vs.repair.dao.CategoryDao;
import com.vs.repair.dao.CustomerDao;
import com.vs.repair.dao.ItemDao;
import com.vs.repair.model.CategoryEntity;
import com.vs.repair.model.CustomerEntity;
import com.vs.repair.model.ItemEntity;
import com.vs.repair.model.ResponseModel;
import com.vs.repair.model.request.ItemRequest;

@RestController
@Transactional
@RequestMapping(value = "/item")
public class ItemController {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CustomerDao customerDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class.getName());
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addItem(@RequestBody ItemRequest request){
		LOGGER.debug("Received request to add a item.");

		CustomerEntity customer = customerDao.findById(request.getCustomerId());
		CategoryEntity category = categoryDao.findById(request.getCategoryId());
		ItemEntity entity = new ItemEntity(request, category, customer);
		
		if(itemDao.addItem(entity))
			return new ResponseModel(HttpStatus.CREATED.value(), "Item added");
		else
			return new ResponseModel(HttpStatus.FORBIDDEN.value(), "Item not added");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<ItemEntity> listItem() {
		LOGGER.debug("Received request to get list of item.");
		return itemDao.list();
    }
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ItemEntity getItemById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a item by id.");		
		return itemDao.findById(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteItem(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a item.");
		ItemEntity entity = itemDao.findById(id);
		if(entity != null){
			itemDao.deleteItem(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Item deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Item not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateItem(@RequestBody ItemRequest request, @RequestParam("id") long id){
		LOGGER.debug("Received request to update a item.");
		ItemEntity ItemEntity = itemDao.findById(id);
		CustomerEntity customer = customerDao.findById(request.getCustomerId());
		CategoryEntity category = categoryDao.findById(request.getCategoryId());
		if(ItemEntity != null){
			itemDao.updateItem(id, new ItemEntity(request, category, customer));
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Item updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Item not found");			
		}
	}
}
