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
import com.vs.repair.dao.CategoryWizardDao;
import com.vs.repair.model.CategoryEntity;
import com.vs.repair.model.CategoryWizardEntity;
import com.vs.repair.model.ResponseModel;

@RestController
@RequestMapping("/categoryWizard")
@Transactional
public class CategoryWizardController {

	@Autowired
	private CategoryWizardDao categoryWizardDao;

	@Autowired
	private CategoryDao categoryDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class.getName());

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<CategoryWizardEntity> getCategoryWizards() {
		return categoryWizardDao.listCategoryWizards();
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public CategoryWizardEntity getCategoryWizardById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a category wizard by id.");		
		return categoryWizardDao.findById(id);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addCategoryWizard(@RequestParam("categoryId") long categoryId, @RequestBody CategoryWizardEntity wizardEntity) {
		CategoryEntity categoryEntity = categoryDao.findById(categoryId);
		wizardEntity.setCategoryEntity(categoryEntity);
		
		if(categoryWizardDao.save(wizardEntity))
			return new ResponseModel(HttpStatus.CREATED.value(), "Category Wizard added");
		else
			return new ResponseModel(HttpStatus.BAD_REQUEST.value(), "Unable to process request");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateCategoryWizards(@RequestBody CategoryWizardEntity entity, @RequestParam("id") long id) {
		LOGGER.debug("Received request to update a Category Wizard.");
		CategoryWizardEntity categoryWizardentity = categoryWizardDao.findById(id);
		if(categoryWizardentity != null){
			categoryWizardDao.updateCategoryWizard(id, entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Category Wizard updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Category Wizard not found");			
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteCategoryWizards(@RequestParam("id") long id) {
		LOGGER.debug("Received request to delete a Category Wizard.");
		CategoryWizardEntity entity = categoryWizardDao.findById(id);
		if(entity != null){
			categoryWizardDao.deleteCategoryWizard(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Category Wizard deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Category Wizard not found");			
		}
	}
}
