package com.infosys.admin.directory.controller;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.admin.directory.model.EmployeeModel;
import com.infosys.admin.directory.model.QueryModel;
import com.infosys.admin.directory.service.BuildResponseAdminService;


@RestController
@Validated
@RequestMapping(value = "/admin.restapi")
public class AdminController {
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuildResponseAdminService adminService;
	
	
	
	@GetMapping(value= {"","/"})
	@ResponseBody
	public String ping(){
		return "ping";
	}
	

		
	
		
	// http://localhost:{0}/adminclient/admin.restapi/add
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes= "application/json",produces = "application/json")
		public ResponseEntity<Object> add(
				@RequestBody EmployeeModel emp
				) {
			
			
			logger.info("Insert initiated");
			logger.info("employee Object",emp);
			
			return adminService.add(emp);
		}
	
	
	
	// http://localhost:{0}/adminclient/admin.restapi/addEmployees
	@RequestMapping(value = "/addEmployees", method = RequestMethod.POST,consumes= "application/json",produces = "application/json")
		public ResponseEntity<Object> addEmployees(
				@RequestBody EmployeeModel[] emparr
				) {
			
			
			logger.info("Inserts initiated");
			logger.info("employee Objects"+emparr);
			
			return adminService.addEmployees(emparr);
		}
		
	// http://localhost:{0}/adminclient/admin.restapi/updateEmployees
		@RequestMapping(value = "/updateEmployees", method = RequestMethod.PUT,consumes= "application/json",produces = "application/json")
			public ResponseEntity<Object> updateEmployees(
					@RequestBody QueryModel query
					) {
				
				
				logger.info("Upated initiated");
				logger.info("Update with query initiated",query);
				
				return adminService.processQuery(query);
		
			}
			
			
		// http://localhost:{0}/adminclient/admin.restapi/updates
			@RequestMapping(value = "/updates", method = RequestMethod.PUT,consumes= "application/json",produces = "application/json")
	
				public ResponseEntity<Object> updates(
						@RequestBody QueryModel[] queries
						) {
				
					logger.info("Upated initiated");
					logger.info("Updates with query initiated");
					
					return adminService.processQueries(queries);

				}
				
				@RequestMapping("*")
				public ResponseEntity<Object>  fallbackMethod(){
					
					logger.info("Not a valid URL");
					return adminService.fallbackMethod();
				}
		

}
