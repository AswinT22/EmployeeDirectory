package com.infosys.directory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.directory.service.AdminService;


@RestController
@Validated
@RequestMapping(value = "/test.admin.restapi")
public class TestController {

	

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AdminService adminService;
	
	
	
	@GetMapping(value= {"","/"})
	@ResponseBody
	public String ping(){
		return "ping";
	}
	
//	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes= "application/json",produces = "application/json")
//	//@GetMapping(value= "/add")
//		public ResponseEntity<Object> add(
//				RequestEntity<Object> request
//				) {
//			
//			
//			logger.info("Inserted initiated");
////			logger.info("Object",emp);
////			
////			return adminService.add(emp);
//		//	return adminService.fallbackMethod();
//		}
}
