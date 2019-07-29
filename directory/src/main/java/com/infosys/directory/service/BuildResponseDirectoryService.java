package com.infosys.directory.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infosys.directory.DirectoryConfiguration;
import com.infosys.directory.dto.EmployeeDTO;
import com.infosys.directory.exceptions.InvalidValueException;
import com.infosys.directory.utility.EmployeeInformation;
import com.infosys.directory.utility.ExceptionControllerAdvice;

@Service
public class BuildResponseDirectoryService {
	
	@Autowired
	DirectoryService directoryService;
	@Autowired
	ExceptionControllerAdvice expectionHandler;

	@Autowired
	DirectoryConfiguration config;
	Logger logger = LoggerFactory.getLogger(this.getClass());
		public ResponseEntity<Object> fetchAllEmployees() {

	

		
		logger.info("Fetching All Employees ");
		List<EmployeeDTO> employeeDTOs=directoryService.fetchAllEmployees();
		
		
		EmployeeInformation empInfo=
				new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("DirectoryService.Success"),employeeDTOs);
		
		
		return empInfo.buildEmployeeInfo();
		}
				
		
		
		public ResponseEntity<Object> fetchAllEmployeesByDepartment(String department) {
		
				try {
					
						
					
				List<EmployeeDTO> employeeDTOs = directoryService.fetchAllEmployeesByDepartment(department);
				
		
				
				logger.info("Employee details : {}", employeeDTOs);
				
				EmployeeInformation empInfo=
						new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("DirectoryService.Success"),employeeDTOs);
				
				
				return empInfo.buildEmployeeInfo();
				
				}
				
				catch(InvalidValueException ex) {
					
					logger.info("Exception occured"+config.getMessage(ex.getMessage()));
					return expectionHandler.handleException(new InvalidValueException(config.getMessage(ex.getMessage())));
				
				
				}
			}
		
		
		
		public ResponseEntity<Object> fetchAllEmployeeByDepartmentAndSalary(String department,String salary) {
		
		
		
		try {
			
				
					
				

				List<EmployeeDTO> employeeDTOs =directoryService.fetchAllEmployeeByDepartmentAndSalary(department, salary);
		
				
		
				logger.info("Total number of employees for department: "+department
						+" and salary greater than "+salary+" is "+employeeDTOs.size(), employeeDTOs);
				
				
				EmployeeInformation empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("DirectoryService.Success"),employeeDTOs);
				return empInfo.buildEmployeeInfo();
			}
		
		
		
		
		
		
		catch(InvalidValueException ex) {
			
			logger.info("Exception occured"+config.getMessage(ex.getMessage()));
			return expectionHandler.handleException(new InvalidValueException(config.getMessage(ex.getMessage())));
		
		
		}
		
	
	
	}
		
		public ResponseEntity<Object> fetchAllEmployeeByDepartmentAndAge(String department,int n)  {
		
		
		
		try {
					
				
						List<EmployeeDTO> employeeDTOs = directoryService.fetchAllEmployeeByDepartmentAndAge(department, n);
			
						logger.info("Total number of employees for department: "+department
								+" greater than and equal to"+ n+" Years  is "+employeeDTOs.size(), employeeDTOs);
						
						
						EmployeeInformation empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("DirectoryService.Success"),employeeDTOs);
						return empInfo.buildEmployeeInfo();
			}
		
		
		
		
		
		
		catch(InvalidValueException ex) {
			
			logger.info("Exception occured"+config.getMessage(ex.getMessage()));
			return expectionHandler.handleException(new InvalidValueException(config.getMessage(ex.getMessage())));
		
		
		}
		
	
	
	}

		public ResponseEntity<Object> fetchAllEmployeeByDepartmentAndMetric(String department,String value, String metric)  {
		
		logger.info("In the service");
		
		try {
				
				List<EmployeeDTO> employeeDTOs=directoryService.fetchAllEmployeeByDepartmentAndMetric(department, value, metric);								
				
				
				logger.info("Total number of employees for department: "+department
						+" greater than "+ value+" "+metric+"  is "+employeeDTOs.size(), employeeDTOs);
				
				
				EmployeeInformation empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("DirectoryService.Success"),employeeDTOs);
				return empInfo.buildEmployeeInfo();
			}
		
		
		
		
		
		
		catch(InvalidValueException ex) {
			
			logger.info("Exception occured"+config.getMessage(ex.getMessage()));
			return expectionHandler.handleException(new InvalidValueException(config.getMessage(ex.getMessage())));
		
		
		}
		
	
	
	}

		public  ResponseEntity<Object> fallbackMethod() {
			
				  return expectionHandler.handleException(new Exception("Not a Valid URL"),HttpStatus.BAD_REQUEST);
			}
		
				
				
				
				
				
				
}
