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
import com.vs.repair.model.PrivilegesEntity;
import com.vs.repair.model.ResponseModel;

@RestController
@Transactional
@RequestMapping(value = "/privilege")
public class PrivilegesController {

	@Autowired
	private PrivilegesDao privilegesDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegesController.class.getName());
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addPrivileges(@RequestBody PrivilegesEntity request){
		LOGGER.debug("Received request to add a privilege.");
		if(privilegesDao.addPrivilege(request))
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Privilege added");
		else
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Privilege not added");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<PrivilegesEntity> listPrivileges() {
		LOGGER.debug("Received request to get list of privileges.");
		return privilegesDao.list();
    }
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public PrivilegesEntity getPrivilegesById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a order privilege by id.");		
		return privilegesDao.findById(id);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deletePrivileges(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a privilege.");
		PrivilegesEntity entity = privilegesDao.findById(id);
		if(entity != null){
			privilegesDao.deletePrivilege(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Privilege deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Privilege not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updatePrivileges(@RequestBody PrivilegesEntity entity, @RequestParam("id") long id){
		LOGGER.debug("Received request to update a privilege.");
		PrivilegesEntity privilegesEntity = privilegesDao.findById(id);
		if(privilegesEntity != null){
			privilegesDao.updatePrivilege(id, entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Privilege updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Privilege not found");			
		}
	}
}
