//package com.infosys.directory.controller;
//
//
//
//
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.infosys.directory.model.EmployeeModel;
//import com.infosys.directory.model.QueryModel;
//import com.infosys.directory.service.AdminService;
//import com.infosys.directory.service.BuildResponseAdminService;
//
//
//@RestController
//@Validated
//@RequestMapping(value = "/admin.restapi")
//public class AdminController {
//	
//	
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	BuildResponseAdminService adminService;
//	
//	
//	
//	@GetMapping(value= {"","/"})
//	@ResponseBody
//	public String ping(){
//		return "ping";
//	}
//	
//	// http://localhost:4343/EmployeeDirectory/admin.restapi/add
//		
//	
//		//public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) 
//	
//	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes= "application/json",produces = "application/json")
////	//@GetMapping(value= "/add")
//		public ResponseEntity<Object> add(
//				@RequestBody EmployeeModel emp
//				) {
//			
//			
//			logger.info("Insert initiated");
//			logger.info("employee Object",emp);
//			
//			return adminService.add(emp);
//		//	return adminService.fallbackMethod();
//		}
//	
//	
//	//these two call should call the same service make Changes
//	
//	@RequestMapping(value = "/addEmployees", method = RequestMethod.POST,consumes= "application/json",produces = "application/json")
//	//@GetMapping(value= "/addEmployees")
//		public ResponseEntity<Object> addEmployees(
//				@RequestBody EmployeeModel[] emparr
//				) {
//			
//			
//			logger.info("Inserts initiated");
//			logger.info("employee Objects"+emparr);
//			
//			return adminService.addEmployees(emparr);
//		//	return adminService.fallbackMethod();
//		}
//		
//		
//		@RequestMapping(value = "/updateEmployees", method = RequestMethod.POST,consumes= "application/json",produces = "application/json")
//		//@GetMapping(value= "/addEmployees")
//			public ResponseEntity<Object> updateEmployees(
//					@RequestBody QueryModel query
//					) {
//				
//				
//				logger.info("Upated initiated");
//				logger.info("Update with query initiated",query);
//				
//				return adminService.processQuery(query);
//			//	return adminService.fallbackMethod();
//			}
//			
//			
//			
//			@RequestMapping(value = "/updates", method = RequestMethod.POST,consumes= "application/json",produces = "application/json")
//			//@GetMapping(value= "/addEmployees")
//				public ResponseEntity<Object> updates(
//						@RequestBody QueryModel[] queries
//						) {
//				
//					logger.info("Upated initiated");
//					logger.info("Updates with query initiated");
//					
//					return adminService.processQueries(queries);
//				//	return adminService.fallbackMethod();
//				}
//				
//				@RequestMapping("*")
//				public ResponseEntity<Object>  fallbackMethod(){
//					
//					logger.info("Not a valid URL");
//					return adminService.fallbackMethod();
//				}
//		
//
//}
