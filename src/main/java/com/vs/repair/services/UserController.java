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

import com.vs.repair.dao.PrivilegesDao;
import com.vs.repair.dao.UserDao;
import com.vs.repair.model.PrivilegesEntity;
import com.vs.repair.model.ResponseModel;
import com.vs.repair.model.UserEntity;
import com.vs.repair.model.request.UserRequest;

@RestController
@Transactional
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PrivilegesDao privilegesDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addUser(@RequestBody UserRequest request){
		LOGGER.debug("Received request to add a user.");
		PrivilegesEntity privilegeEntity = privilegesDao.findById(request.getPrivilegeId());
		
		UserEntity entity = new UserEntity(request, privilegeEntity);
		
		if(userDao.addUser(entity))
			return new ResponseModel(HttpStatus.CREATED.value(), "User added");
		else
			return new ResponseModel(HttpStatus.BAD_REQUEST.value(), "User not added");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<UserEntity> listUsers() {
		LOGGER.debug("Received request to get list of users.");
		return userDao.list();
    }
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public UserEntity getUserById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a user privilege by id.");		
		return userDao.findById(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteUser(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a user.");
		UserEntity entity = userDao.findById(id);
		if(entity != null){
			userDao.deleteUser(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "User deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "USer not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateUser(@RequestBody UserRequest request, @RequestParam("id") long id){
		LOGGER.debug("Received request to update a privilege.");
		UserEntity userEntity = userDao.findById(id);
		PrivilegesEntity privilegeEntity = privilegesDao.findById(request.getPrivilegeId());
		
		UserEntity entity = new UserEntity(request, privilegeEntity);
		if(userEntity != null){
			userDao.updateUser(id, entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "User updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "User not found");			
		}
	}
}
