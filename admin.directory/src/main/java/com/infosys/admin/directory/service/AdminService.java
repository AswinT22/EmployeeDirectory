package com.infosys.admin.directory.service;

import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.infosys.admin.directory.AdminConfiguration;
import com.infosys.admin.directory.dto.EmployeeDTO;
import com.infosys.admin.directory.entity.Employee;
import com.infosys.admin.directory.exceptions.InvalidValueException;
import com.infosys.admin.directory.model.EmployeeModel;
import com.infosys.admin.directory.model.QueryModel;
import com.infosys.admin.directory.repository.CustomRepositoryInterface;
import com.infosys.admin.directory.repository.EmployeeRepository;
import com.infosys.admin.directory.utility.EmployeeServiceUtil;


@Service
public class AdminService {

	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	CustomRepositoryInterface custRepo;
	

	@Autowired
	AdminConfiguration config;
	
	
	@Autowired
	EmployeeServiceUtil employeeServiceUtil;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Transactional
	public boolean add(EmployeeModel employees) throws Exception{
		
		try {
				EmployeeDTO employeeDTO= EmployeeDTO.modelOf(employees);
				
					
					if(employeeServiceUtil.validateEmployee(employeeDTO))
					{
						employeeRepo.saveAndFlush(employeeDTO.createEntity());
						return true;
					}
					else 
					return false;
			
		}
		
		catch(Exception ex) {
			logger.info("Creation failed");
					throw new Exception(ex.getMessage());
					
					
				}
}
	
	@Transactional
	public List<EmployeeDTO> addEmployees(List<EmployeeDTO> employeeDTOs)throws Exception {
		
		try {
			
				List<EmployeeDTO> failedUpdate=new ArrayList<>();
				
		
				
				
				
				for(EmployeeDTO employeeDTO:employeeDTOs) 
					if(employeeServiceUtil.validateEmployee(employeeDTO))

						employeeRepo.saveAndFlush(employeeDTO.createEntity());
				
					else 
						failedUpdate.add(employeeDTO);
				
				
					
				
				
		return failedUpdate;
					
			
		}
		
		
		catch(Exception ex) {
			logger.info("Creation failed");
			throw new Exception(ex.getMessage());
			
			
		}
	}
	
	@Transactional
	public List<EmployeeDTO>  processQuery(QueryModel query) throws InvalidValueException,Exception{
		logger.info("Processing Query");
		try {
				if(!employeeServiceUtil.isValidQuery(query)	)	
					throw new InvalidValueException("AdminService.NOT_A_VALID_QUERY");
				
				
				List<Employee> empList=custRepo.getData(query.getQueryMetric().toLowerCase(), query.getQueryValue());
				
				logger.info("got data");
				
				empList=employeeServiceUtil.updateEmployeeList(empList,query.getChangeMetric(),query.getChangeValue());
				
				logger.info("got updateList");
				employeeRepo.saveAll(empList);
				
				employeeRepo.flush();
				List<EmployeeDTO>empListDTOS=empList.parallelStream().map(x->EmployeeDTO.valueOf(x)).collect(Collectors.toList());
				logger.info("Rows Affected :"+empList.size());
				return empListDTOS;
		
		}
		
		
		
		catch(InvalidValueException ex) {
			throw new InvalidValueException(config.getMessage(ex.getMessage()));
				
				
				}
		catch(Exception ex) {
		
			throw new Exception(ex.getMessage());
			
			
		}
		
	}
	
	@Transactional
	public List<QueryModel> processQueries(QueryModel[] queries) throws Exception{
		logger.info("Processing Query");
		List<QueryModel> failedUpdate=new ArrayList<>();
		try {
			
			
			
			for(QueryModel query:queries) {
			try {
				processQuery(query);
			}
			
			catch(Exception e){
				
				
				failedUpdate.add(query);
			}
			}
				
					return failedUpdate;

		
		}
		
		
		
		catch(Exception ex) {
			throw new Exception(ex.getMessage());
			
			
		}
		
	}}


