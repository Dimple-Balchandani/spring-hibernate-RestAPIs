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

import com.vs.repair.dao.OrderDao;
import com.vs.repair.dao.UserDao;
import com.vs.repair.model.OrderEntity;
import com.vs.repair.model.ResponseModel;
import com.vs.repair.model.UserEntity;
import com.vs.repair.model.request.OrderRequest;

@Transactional
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private UserDao userDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class.getName());
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addOrder(@RequestBody OrderRequest request){
		LOGGER.debug("Received request to add a order.");
		UserEntity userShipper = userDao.findById(request.getShipperId());
		UserEntity userProvider = userDao.findById(request.getProviderId());
		
		OrderEntity entity = new OrderEntity(request, userShipper, userProvider);
		
		if(orderDao.addOrder(entity)) {
			return new ResponseModel(HttpStatus.CREATED.value(), "Order added");
		} else
			return new ResponseModel(HttpStatus.BAD_REQUEST.value(), "Order not added");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<OrderEntity> listorders() {
		LOGGER.debug("Received request to get list of orders.");
		return orderDao.list();
    }
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public OrderEntity getOrderById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a order by id.");		
		return orderDao.findById(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteOrder(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a order.");
		OrderEntity entity = orderDao.findById(id);
		if(entity != null){
			orderDao.deleteOrder(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "order deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "order not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateOrders(@RequestBody OrderRequest request, @RequestParam("id") long id){
		LOGGER.debug("Received request to update a order.");
		OrderEntity orderEntity = orderDao.findById(id);
		UserEntity userShipper = userDao.findById(request.getShipperId());
		UserEntity userProvider = userDao.findById(request.getProviderId());
		
		if(orderEntity != null){
			orderDao.updateOrder(id, new OrderEntity(request,userShipper,userProvider));
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "order updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "order not found");			
		}
	}

}
