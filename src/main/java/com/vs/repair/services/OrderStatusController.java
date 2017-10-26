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
import com.vs.repair.dao.OrderStatusDao;
import com.vs.repair.dao.StatusDao;
import com.vs.repair.model.OrderEntity;
import com.vs.repair.model.OrderStatusEntity;
import com.vs.repair.model.ResponseModel;
import com.vs.repair.model.StatusEntity;
import com.vs.repair.model.request.OrderStatusRequest;

@RestController
@Transactional
@RequestMapping(value = "/status")
public class OrderStatusController {

	@Autowired
	private OrderStatusDao orderStatusDao;
	
	@Autowired
	private StatusDao statusDao;
	
	@Autowired
	private OrderDao orderDao;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegesController.class.getName());
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addStatus(@RequestBody OrderStatusRequest request){
		LOGGER.debug("Received request to add a status.");
		OrderEntity order = orderDao.findById(request.getOrderId());
		StatusEntity status = statusDao.findById(request.getStatusId());
		
		OrderStatusEntity entity = new OrderStatusEntity(request, order, status);
		if(orderStatusDao.addOrderStatus(entity))
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "status added");
		else
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "status not added");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<OrderStatusEntity> listStatus() {
		LOGGER.debug("Received request to get list of status.");
		return orderStatusDao.list();
    }
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public OrderStatusEntity getOrderStatusById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a order status by id.");		
		return orderStatusDao.findById(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseModel deleteStatus(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a status.");
		OrderStatusEntity entity = orderStatusDao.findById(id);
		if(entity != null){
			orderStatusDao.deleteOrderStatus(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "status deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "status not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateStatus(@RequestBody OrderStatusRequest request, @RequestParam("id") long id){
		LOGGER.debug("Received request to update a status.");
		
		OrderStatusEntity orderStatus = orderStatusDao.findById(id);
		OrderEntity order = orderDao.findById(request.getOrderId());
		StatusEntity status = statusDao.findById(request.getStatusId());
		
		OrderStatusEntity entity = new OrderStatusEntity(request, order, status);
		if(orderStatus != null){
			orderStatusDao.updateOrderStatus(id, entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "status updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "status not found");			
		}
	}
}
