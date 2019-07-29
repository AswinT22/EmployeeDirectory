package com.infosys.admin.directory.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infosys.admin.directory.AdminConfiguration;
import com.infosys.admin.directory.dto.EmployeeDTO;
import com.infosys.admin.directory.exceptions.InvalidValueException;
import com.infosys.admin.directory.model.EmployeeModel;
import com.infosys.admin.directory.model.QueryModel;
import com.infosys.admin.directory.utility.EmployeeInformation;
import com.infosys.admin.directory.utility.ExceptionControllerAdvice;
import com.infosys.admin.directory.utility.UpdateInformation;

@Service
public class BuildResponseAdminService {
	
	@Autowired
	ExceptionControllerAdvice expectionHandler;
	
	@Autowired
	AdminService service;
	
	@Autowired
	AdminConfiguration config;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	

	public ResponseEntity<Object> add(EmployeeModel employees) {
		
		try {
				
						
						
			EmployeeInformation	empInfo;
					
			if(!service.add(employees)) {
						logger.info("Employee creation failed ", employees);
						logger.info("No of inserts failed: 1",1);
						empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.FAILED"),EmployeeDTO.modelOf(employees));
						return empInfo.buildEmployeeInfo();		
					}
					
					
					empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.ALL_CREATED"),EmployeeDTO.modelOf(employees));
					logger.info(" insert was successful");
					logger.info("No of inserts : 1",employees);
					return empInfo.buildEmployeeInfo();
			
		}
		
		catch(Exception ex) {
			logger.info("Creation failed");
					return expectionHandler.handleException(ex,HttpStatus.BAD_REQUEST);
					
					
				}
}
	

	public ResponseEntity<Object> addEmployees(EmployeeModel[] emparr) {
		
		try {
			List<EmployeeDTO> employeeDTOs= 
					Arrays.asList(emparr).parallelStream()
					.map(x->EmployeeDTO.modelOf(x)).collect(Collectors.toList());
				
				List<EmployeeDTO> failedUpdate=service.addEmployees(employeeDTOs);
					
				
						
				
		
				logger.info("Employee Input List: {}", employeeDTOs);
			
					EmployeeInformation empInfo;
					if(failedUpdate.size()>0) {
						logger.info("Employee failed List: {}", failedUpdate);
						logger.info("No of inserts failed:"+failedUpdate.size(),failedUpdate.size());
						empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.PARTIAL_Success"),failedUpdate);
						
						if(failedUpdate.size()==employeeDTOs.size())
							empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.FAILED"),failedUpdate);
							
						
						return empInfo.buildEmployeeInfo();
					}
					
					
					
					empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.ALL_CREATED"),employeeDTOs);
					logger.info("All insert was successful");
					logger.info("No of inserts :"+employeeDTOs.size(), employeeDTOs.size());
					return empInfo.buildEmployeeInfo();
			
		}
		
		
		catch(Exception ex) {
			logger.info("Creation failed");
			return expectionHandler.handleException(ex,HttpStatus.BAD_REQUEST);
			
			
		}
	}
	

	public ResponseEntity<Object>  processQuery(QueryModel query){
		logger.info("Processing Query");
		try {
				
				List<EmployeeDTO>empListDTOS=service.processQuery(query);
				
				EmployeeInformation empInfo=new EmployeeInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.ALL_UPDATED"),empListDTOS);
				
				logger.info("All updates was successful");
				
				
				
				
				logger.info("Updates successful");
				return empInfo.buildEmployeeInfo();
		
		}
		
		
		
		catch(InvalidValueException ex) {
			logger.info("Updates failed");
			ex.printStackTrace();
					return expectionHandler.handleException(new InvalidValueException(config.getMessage(ex.getMessage())));
				
				
				}
		catch(Exception ex) {
			logger.info("Updates failed");
			ex.printStackTrace();
			return expectionHandler.handleException(ex,HttpStatus.BAD_REQUEST);
			
			
		}
		
	}
	
	
	public ResponseEntity<Object>  processQueries(QueryModel[] queries){
		logger.info("Processing Query");
		List<QueryModel> failedUpdate=new ArrayList<>();
		try {
			
			
			
				//List<EmployeeDTO>empListDTOS=empList.parallelStream().map(x->EmployeeDTO.valueOf(x)).collect(Collectors.toList());
				failedUpdate=service.processQueries(queries);
				UpdateInformation updateInfo;
				if(failedUpdate.size()>0) {
					logger.info("Update failed List: {}", failedUpdate);
					logger.info("No of Updates failed:"+failedUpdate.size(),failedUpdate.size());
					updateInfo=new UpdateInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.PARTIAL_Success"),failedUpdate);
					
					if(failedUpdate.size()==queries.length)
						updateInfo=new UpdateInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.FAILED"),failedUpdate);
						
					
					return updateInfo.buildUpdateInfo();
				}
				
				
				
				
				updateInfo=new UpdateInformation(HttpStatus.ACCEPTED,config.getMessage("AdminService.ALL_QUERY_UPDATED"),Arrays.asList(queries));
				
				logger.info("All updates was successful");
				//.info("Rows Affected :"+empList.size());
				
				
				
				return updateInfo.buildUpdateInfo();
				
			
			
			
		
		}
		
		
		
		catch(Exception ex) {
			logger.info("Updates failed");
			ex.printStackTrace();
			return expectionHandler.handleException(ex,HttpStatus.BAD_REQUEST);
			
			
		}
		
	}
	public  ResponseEntity<Object> fallbackMethod() {
		
		  return expectionHandler.handleException(new Exception("Not a Valid URL"),HttpStatus.BAD_REQUEST);
	}

}
