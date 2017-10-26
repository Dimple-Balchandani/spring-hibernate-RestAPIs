package com.vs.repair.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vs.repair.utility.Constants;
import com.vs.repair.utility.FileUtility;

@RestController
@RequestMapping("/")
public class MainController {

	private final static Logger LOGGER = LoggerFactory.getLogger(MainController.class.getName());
	
	@Autowired
	private FileUtility fileUtility;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)	
	public String index(){
		return "<b>Started</b>";
	}
	
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public String version() {
		LOGGER.info("Inside Main Controller");
		return "0.0.1";
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.POST)
	public String getJson(@RequestParam("fileName") String filename){
		return fileUtility.readResourceFile(Constants.DUMMY_JSON_FOLDER, filename);
	}
}
