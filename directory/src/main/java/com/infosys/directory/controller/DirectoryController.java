package com.infosys.directory.controller;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.directory.DirectoryConfiguration;
import com.infosys.directory.repository.CustomRepositoryInterface;
import com.infosys.directory.repository.EmployeeRepository;
import com.infosys.directory.service.BuildResponseDirectoryService;
import com.infosys.directory.service.DirectoryService;
import com.infosys.directory.utility.EmployeeServiceUtil;
import com.infosys.directory.utility.ExceptionControllerAdvice;


@RestController
@Validated
@RequestMapping(value = "/restapi")

public class DirectoryController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuildResponseDirectoryService service;

	
	
	// Fetches all Employee 
	
	@GetMapping(value =  {"/employees","/employees/"},produces = "application/json" ) 
	public ResponseEntity<Object> fetchAllEmployee()  {
		//directoryService=new DirectoryService(employeeRepo, custRepo, expectionHandler, config, employeeServiceUtil);
		logger.info("Fetching All Employees");
		return service.fetchAllEmployees();
	

	}
	

//	// http://localhost:4343/EmployeeDirectory/restapi/employees/department?Department=x
			@GetMapping(value = "/employees/department",produces ="application/json")
			
		public ResponseEntity<Object> fetchAllEmployeeByDepartment(
					@RequestParam("Department") String department) {
				
				
				//directoryService=new DirectoryService(employeeRepo, custRepo, expectionHandler, config, employeeServiceUtil);
				logger.info("Fetching All Employee By Department  "+department.toUpperCase());
				
				return service.fetchAllEmployeesByDepartment(department.toUpperCase());
			}
	
//	// http://localhost:4343/EmployeeDirectory/restapi/employees/{deparment}?Salary=x
	@GetMapping(value = "/employees/{department}",produces = "application/json")
	
	public ResponseEntity<Object> fetchAllEmployeeByDepartmentAndSalary(
			@PathVariable("department")  String department,
			@RequestParam("Salary")    @Pattern(regexp="^[0-9]*\\.?[0-9]+$",message="Should be a number")String salary) {
		
		
		//directoryService=new DirectoryService(employeeRepo, custRepo, expectionHandler, config, employeeServiceUtil);
		logger.info("Fetching All Employee By Department  "+department.toUpperCase()+" And Salary Greater Than "+salary);
		
		return service.fetchAllEmployeeByDepartmentAndSalary(department.toUpperCase(), salary);
	}
	
	
//	// http://localhost:4343/EmployeeDirectory/restapi/employees/{department}/search?Age=x
	@GetMapping(value = "/employees/{department}/search",produces = "application/json")
	public ResponseEntity<Object> fetchAllEmployeeByDepartmentAndAge(
			
			@PathVariable("department")  String department,
			@RequestParam("Age")   @Pattern(regexp="^[0-9]*\\.?[0-9]+$",message="Should be a number") String Age)  {
		
		
		//directoryService=new DirectoryService(employeeRepo, custRepo, expectionHandler, config, employeeServiceUtil);
		logger.info("Fetching All Employee By Department  "+department.toUpperCase()+" And Age Greater Than or equal to"+Age);
		
		return service.fetchAllEmployeeByDepartmentAndAge(department.toUpperCase(), Integer.valueOf(Age));
	}
	
//	// http://localhost:4343/EmployeeDirectory/restapi/employees/{department}/search/{metric}?value=x
	@GetMapping(value = "/employees/{department}/search/{metric}",produces = "application/json")
	public ResponseEntity<Object> fetchAllEmployeeByDepartmentAndAge
	(@PathVariable("department")  String department,
			
			@RequestParam("value")  String value,
		    
	@PathVariable("metric")  String metric  )  {
		
		
		
		logger.info("Total number of employees for department: "+department
				+" greater than or equal to"+ value +" "+metric);
		
		//directoryService=new DirectoryService(employeeRepo, custRepo, expectionHandler, config, employeeServiceUtil);
		
		return service.fetchAllEmployeeByDepartmentAndMetric(department.toUpperCase(), value,metric.toUpperCase());
	}
	
	@GetMapping(value="/")
	@ResponseBody
	public String ping(){
		return "ping";
	}
	
	
	@RequestMapping(value="**")
	public ResponseEntity<Object>  fallbackMethod(){
		
		logger.info("Not a valid URL");
		//directoryService=new DirectoryService(employeeRepo, custRepo, expectionHandler, config, employeeServiceUtil);
		return service.fallbackMethod();
	}
}
