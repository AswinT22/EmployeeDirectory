package com.infosys.directory.service;

import java.math.BigInteger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infosys.directory.DirectoryConfiguration;
import com.infosys.directory.dto.EmployeeDTO;
import com.infosys.directory.entity.Employee;
import com.infosys.directory.repository.CustomRepositoryInterface;
import com.infosys.directory.repository.EmployeeRepository;
import com.infosys.directory.exceptions.*;
import com.infosys.directory.utility.*;




@Service
public class DirectoryService {
	
	
	

	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	CustomRepositoryInterface custRepo;
	@Autowired
	DirectoryConfiguration config;

	
	@Autowired
	EmployeeServiceUtil employeeServiceUtil;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	public DirectoryService(EmployeeRepository rep,CustomRepositoryInterface cusR,
//			ExceptionControllerAdvice expectionHandle,DirectoryConfiguration config,
//
//	EmployeeServiceUtil employeeServiceUtil) {
//		
//		employeeRepo=rep;
//		custRepo=cusR;
//		this.expectionHandler=expectionHandle;
//		this.config=config;
//		this.employeeServiceUtil=employeeServiceUtil;
//	}
	
	public List<EmployeeDTO> fetchAllEmployees() {

		
		List<EmployeeDTO> employeeDTOs = employeeRepo.findAll().parallelStream()
										.map(x->EmployeeDTO.valueOf(x)).collect(Collectors.toList());
		

		
		logger.info("Employee details : {}", employeeDTOs);
		
		
		return employeeDTOs;
	}
		public List<EmployeeDTO> fetchAllEmployeesByDepartment(String department) throws InvalidValueException {
		
				try {
					
					if(!employeeServiceUtil.isValidMetric(department,config.validDepartments())) 
						throw new InvalidValueException("DirectoryService.DEPARTMENT_NOT_FOUND");
						
					
				List<EmployeeDTO> employeeDTOs = employeeRepo.findByDepartment(department).parallelStream()
												.map(x->EmployeeDTO.valueOf(x)).collect(Collectors.toList());
				
		
				
				logger.info("Employee details : {}", employeeDTOs);
				
				
				
				
				return employeeDTOs;
				
				}
				
				catch(InvalidValueException ex) {
					
					
					throw new InvalidValueException(config.getMessage(ex.getMessage()));
				
				
				}
			}

	public List<EmployeeDTO> fetchAllEmployeeByDepartmentAndSalary(String department,String sal) throws InvalidValueException {
		
		
		
		try {
			
						BigInteger salary=new BigInteger(sal);
						/*
						 * 
						 * Checks for valid department,if not throws an exception
						 * 
						 * 
						 * */
						if(!employeeServiceUtil.isValidMetric(department,config.validDepartments())) 
						
						{
							
							
							/*
							 * 
							 * Checks Salary is greater than Zero,if not throws an exception
							 * 
							 * 
							 * */
							if(salary.signum() < 1)
								throw new InvalidValueException("DirectoryService.INVALID_DEPARTMENT_AND_SALARY_EXCEPTION"); 
								
							
							
							throw new InvalidValueException("DirectoryService.DEPARTMENT_NOT_FOUND");
								
								
							
							
						}
				
				
				
				
				if(salary.signum() < 1)
					throw new InvalidValueException("DirectoryService.INVALID_SALARY_VALUE_EXCEPTION");
					
				

				List<EmployeeDTO> employeeDTOs =employeeRepo.findByDepartmentAndSalaryGreaterThan(department, salary)
						.parallelStream().map(x->EmployeeDTO.valueOf(x)).collect(Collectors.toList());
		
				
		
				logger.info("Total number of employees for department: "+department
						+" and salary greater than "+salary+" is "+employeeDTOs.size(), employeeDTOs);
				
				return employeeDTOs;
			}
		
		
		
		
		
		
		catch(InvalidValueException ex) {
			
			logger.info("Exception occured"+config.getMessage(ex.getMessage()));
			throw new InvalidValueException(config.getMessage(ex.getMessage()));
		
		
		}
		
	
	
	}
	
	public List<EmployeeDTO> fetchAllEmployeeByDepartmentAndAge(String department,int n)  throws InvalidValueException{
		
		
		
		try {
					
						if(!employeeServiceUtil.isValidMetric(department,config.validDepartments())) 
						{
							if(n<0)
								throw new InvalidValueException("DirectoryService.INVALID_AGE_AND_DEPARTMENT");
	
							throw new InvalidValueException("DirectoryService.DEPARTMENT_NOT_FOUND");
							
						}
						
						
						if(n<1)
							throw new InvalidValueException("DirectoryService.INVALID_AGE");
				
						List<EmployeeDTO> employeeDTOs = employeeRepo.findByDepartment(department)
								.parallelStream().map(x->EmployeeDTO.valueOf(x)).filter(x->x.getAge()>=n).collect(Collectors.toList());
			
						logger.info("Total number of employees for department: "+department
								+" greater than and equal to"+ n+" Years  is "+employeeDTOs.size(), employeeDTOs);
						
						
						return employeeDTOs;
			}
		
		
		
		
		
		
		catch(InvalidValueException ex) {
			
			logger.info("Exception occured"+config.getMessage(ex.getMessage()));
			throw new InvalidValueException(config.getMessage(ex.getMessage()));
		
		
		}
		
	
	
	}
	
//
//
//	
	public List<EmployeeDTO>  fetchAllEmployeeByDepartmentAndMetric(String department,String n, String metric)  throws InvalidValueException{
			
			logger.info("In the service");
			
			try {
							//Metric can be anything other than department because department is initial search query 
				
							if(metric.toUpperCase().equals("DEPARTMENT"))
								throw new InvalidValueException("DirectoryService.INVALID_METRICS_EXCEPTION");
								
				
							if(!employeeServiceUtil.isValidMetric(department,config.validDepartments())) 
							
							{
								logger.info("Invalid Department");

								if(!employeeServiceUtil.isValidMetric(metric,config.validMetrics()))
									throw new InvalidValueException("DirectoryService.INVALID_DEPARTMENT_AND_METRIC_EXCEPTION");
									
									
								throw new InvalidValueException("DirectoryService.DEPARTMENT_NOT_FOUND");
								
							}
					
					
							
					
							if(!employeeServiceUtil.isValidMetric(metric,config.validMetrics())) {
								
							
								if(!employeeServiceUtil.isValidN(n, metric))
									throw new InvalidValueException("DirectoryService.INVALID_N_AND_METRIC_EXCEPTION");
								
								
								
								
								throw new InvalidValueException("DirectoryService.INVALID_METRICS_EXCEPTION");

							}
							
							
							if(!employeeServiceUtil.isValidN(n, metric))
								throw new InvalidValueException("DirectoryService.INVALID_N_AND_METRIC_EXCEPTION");
						
					
					List<EmployeeDTO> employeeDTOs=Collections.emptyList();
					
					
					if(metric.equals("MONTH"))
						employeeDTOs = employeeRepo.findByDepartment(department).parallelStream()
							.map(x->EmployeeDTO.valueOf(x)).filter(x->x.getAgeMonths()>=Integer.valueOf(n)).collect(Collectors.toList());
					
					else if(metric.equals("YEAR"))
						employeeDTOs = employeeRepo.findByDepartment(department).parallelStream()
							.map(x->EmployeeDTO.valueOf(x)).filter(x->x.getAge()>=Integer.valueOf(n)).collect(Collectors.toList());
					
					//Modify this to accomodate any metric
					else {
						logger.info("final");
					//department won't be filtered 
					List<Employee> empList=custRepo.getData(metric.toLowerCase(), n);
					//filter it here 
					logger.info("Got data");
					employeeDTOs=empList.parallelStream().map(x->EmployeeDTO.valueOf(x)).filter(x->x.getDepartment().equals(department)).collect(Collectors.toList());
					}
					
					
					
					logger.info("Total number of employees for department: "+department
							+" greater than "+ n+" "+metric+"  is "+employeeDTOs.size(), employeeDTOs);
					
					
					return employeeDTOs;
				}
			
			
			
			
			
			
			catch(InvalidValueException ex) {
				
				logger.info("Exception occured"+config.getMessage(ex.getMessage()));
				throw new InvalidValueException(config.getMessage(ex.getMessage()));
			
			
			}
			
		
		
		}
	
//	public Exception fallbackMethod() {
//		
//		
//		return new Exception("Not a Valid URL");
		
//		logger.info("In fall back method");
//		logger.info("sfgA");
//		if(expectionHandler==null)
//			logger.info("EXc");
//		if(employeeRepo==null)
//			logger.info("repo");
//		return expectionHandler.handleException(new Exception("Not a Valid URL"),HttpStatus.BAD_REQUEST);
//	}
	
	
	

}
