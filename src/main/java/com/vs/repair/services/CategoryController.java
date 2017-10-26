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
import com.vs.repair.model.CategoryEntity;
import com.vs.repair.model.ResponseModel;

@RestController
@Transactional
@RequestMapping("/category/")
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class.getName());
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<CategoryEntity> getCategory(){
		LOGGER.debug("Received request to get category.");
		return categoryDao.listCategory();
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public CategoryEntity getCategoryById(@PathVariable("id") long id){
		LOGGER.debug("Received request to get a category by id.");		
		return categoryDao.findById(id);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addCategory(@RequestBody CategoryEntity request){
		LOGGER.debug("Received request to add a category.");
		categoryDao.addCategory(request);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteCategoryById(@RequestParam("id") long id){
		LOGGER.debug("Received request to delete a category by id.");
		
		CategoryEntity entity = categoryDao.findById(id);
		if(entity != null){
			categoryDao.deleteCategory(entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Category deleted");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Category not found");			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseModel updateCategory(@RequestBody CategoryEntity entity, @RequestParam("id") long id) {
		LOGGER.debug("Received request to update a Category Wizard.");		
		CategoryEntity categoryEntity = categoryDao.findById(id);
		
		if(categoryEntity != null){
			categoryDao.updateCategory(id, entity);
			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Category updated");
		} else {
			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Category not found");			
		}
	}
	
//	@RequestMapping(value = "/delete/name", method = RequestMethod.POST)
//	public ResponseModel deleteCategoryByName(@RequestBody String name){
//		LOGGER.debug("Received request to delete a category by name.");
//		CategoryEntity entity = null;
//		
//		try{
//			entity = categoryDao.findByName(name);
//		} catch(NonUniqueResultException e) {
//			return new ResponseModel(HttpStatus.MULTIPLE_CHOICES.value(), "Multiple category found");			
//		} catch(NoResultException e) {
//			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Category not found");			
//		}
//		
//		if(entity != null){
//			categoryDao.delete(entity);
//			return new ResponseModel(HttpStatus.ACCEPTED.value(), "Category deleted");
//		} else {
//			return new ResponseModel(HttpStatus.NOT_FOUND.value(), "Category not found");			
//		}
//	}
	
}
