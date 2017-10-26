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
import com.vs.repair.dao.FeedbackDao;
import com.vs.repair.dao.ItemDao;
import com.vs.repair.model.CustomerEntity;
import com.vs.repair.model.FeedbackEntity;
import com.vs.repair.model.ItemEntity;
import com.vs.repair.model.ResponseModel;
import com.vs.repair.model.request.FeedbackRequest;

@RestController
@Transactional
@RequestMapping(value = "/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ItemDao itemDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class.getName());
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addFeedback(@RequestBody FeedbackRequest request){
		LOGGER.debug("Received request to add a Feedback.");
		
		CustomerEntity customerEntity = customerDao.findById(request.getCustomerId());
		ItemEntity itemEntity = itemDao.findById(request.getItemId());
		
		FeedbackEntity entity = new FeedbackEntity(request, customerEntity, itemEntity);
		
		if(feedbackDao.addFeedback(entity))
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Feedback added");
		else
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Feedback not added");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<FeedbackEntity> listFeedback() {
		LOGGER.debug("Received request to get list of Feedbacks.");
		return feedbackDao.list();
    }
	
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public FeedbackEntity getFeedbackById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a Feedback by id.");		
		return feedbackDao.findById(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteFeedback(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a Feedback.");
		FeedbackEntity entity = feedbackDao.findById(id);
		if(entity != null){
			feedbackDao.deleteFeedback(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Feedback deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Feedback not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateFeedback(@RequestBody FeedbackRequest request, @RequestParam("id") long id){
		LOGGER.debug("Received request to update a Feedback.");
		
		FeedbackEntity feedbackEntity = feedbackDao.findById(id);		
		CustomerEntity customerEntity = customerDao.findById(request.getCustomerId());
		ItemEntity itemEntity = itemDao.findById(request.getItemId());
		
		FeedbackEntity entity = new FeedbackEntity(request, customerEntity, itemEntity);
		
		if(feedbackEntity != null){
			feedbackDao.updateFeedback(id, entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Feedback updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Feedback not found");			
		}
	}
}
