package com.infosys.directory.utility;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.infosys.directory.DirectoryConfiguration;
import com.infosys.directory.dto.EmployeeDTO;
import com.infosys.directory.entity.Employee;
import com.infosys.directory.exceptions.InvalidValueException;
import com.infosys.directory.model.QueryModel;


@Component
@DependsOn("config")
public class EmployeeServiceUtil {
	
	
	@Autowired
	DirectoryConfiguration config;
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	 final int Month_MAX_VAl=65*12;
	 final int Month_MIN_VAl=18*12;
	 final int Year_MAX_VAl=65;
	 final int Year_MIN_VAl=18;
	 
	 
	 
	 
	
	public  boolean isValidMetric(String metric,String[] metricArray) {
		
		
		try {
			logger.info("Metric Validation "+metric);
		return Arrays.stream(metricArray).anyMatch(x-> x.equals(metric.toUpperCase()));
	}
	catch(Exception ex) {
		
		logger.info("Metric Validation failed  "+metric);
		
		
		return false;
	}
	
	}
	
	public  boolean isValidN(String n,String metric) {
		
		logger.info("in validation "+metric+ "  n:"+n);
		try {
			
			metric=metric.toUpperCase();
		switch(metric) {
		
		case "MONTH":
			int mon=Integer.valueOf(n);
			return (mon>=Month_MIN_VAl && mon<=Month_MAX_VAl)? true:false;
			
		case "YEAR":
			int year=Integer.valueOf(n);
			return (year>= Year_MIN_VAl && year<= Year_MAX_VAl)? true:false;
			
		case "NAME":
				return n.length()>= 1 && n.length()<=80?true:false;
				
		case "GENDER":
			return n.length()>= 1 && n.length()<=10?true:false;
			
		case "SALARY":
			BigInteger sal=new BigInteger(n);
			logger.info("in validation salary:"+n);
			return sal.signum() < 1?false:true;
			
			
		case "DEPARTMENT":
			
			return isValidMetric(n, config.validDepartments());
		
		case "DATEOFBIRTH":
			logger.info("Dob valid");
			 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dob= LocalDate.parse(n,formatters); //if n is not in format dd/MM/yyyy ParseException will be thrown
		    								// Integer.valueOf() throws NumberFormatException
		    								//BigInteger() also throws NumberFormatException
		    								//which is handled in the catch and returned false;
		    								
		    break;
		        
			
		default:
			return false;
		
		
		}
		
		return true;
		}
		
		catch(Exception ex) {
			
			
			
			
			return false;
		}
		
	}
	
public boolean validateEmployee(EmployeeDTO employee)  {
	
		try {
		if(employee.getSalary().signum() < 1)
			
		{	
			logger.info("Invalid salary",employee.getSalary());
			return false;
		}
		
		if(!(employee.getAge() >=18 && employee.getAge() <= 65))
		{	
			logger.info("Invalid age");
			return false;
		}
		
		if(!(employee.getName().length()>= 1 && employee.getName().length()<=80))
		{	
			logger.info("Invalid Name");
			return false;
		}
		
		 if (!(employee.getGender().length()>= 1 && employee.getGender().length()<=10) )
		 {	
				logger.info("Invalid gender");
				return false;
			}
		 
		 if(!isValidMetric(employee.getDepartment().toUpperCase(),config.validDepartments()))
		 {	
//			 	for(int i=0;i<metricArray.length;i++)
//			 		System.out.println(metricArray[i]);
				logger.info("Invalid Department"+employee.getDepartment());
				return false;
			}

		return true;

		}
				catch(Exception ex) {
							return false;
						}
	}


				public boolean isValidQuery(QueryModel query) {
					
					try {
						logger.info("validating query");
					if(!isValidMetric(query.getChangeMetric(), config.validMetrics()))
						
						return false;
						
					if(!isValidMetric(query.getQueryMetric(), config.validMetrics()))
							
							return false;
					
					if(!isValidN(query.getChangeValue(), query.getChangeMetric().toUpperCase()))
						return false;
					
					if(!isValidN(query.getQueryValue(), query.getQueryMetric().toUpperCase()))
						return false;
					
					
					
					return true;
					}
					catch(Exception ex) {
						return false;
					}
				}
				
				
				public List<Employee> updateEmployeeList(List<Employee> empList,String metric,String value) throws InvalidValueException {
				
				
				String changeMetric=metric.toUpperCase();
				logger.info("in update change metric:"+changeMetric+" change value: "+value);
				try {
				 
				switch(changeMetric) {
				
					case"NAME":
						throw new InvalidValueException("DirectoryService.NOT_A_VALID_QUERY");
							
					case "GENDER":
						empList.forEach(x -> x.setGender(value));
						break;
					case "SALARY":
						BigInteger sal=new BigInteger(value);
						empList.forEach(x -> x.setSalary(sal));
						break;

					case "DEPARTMENT":
						
						empList.forEach(x -> x.setDepartment(value));
						break;
					//Everything else is validated other than DOB
					case "DATEOFBIRTH":
						
//						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//					    Date date = formatter.parse(value);
//						
//						
//						
//						LocalDate dob=Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
						 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate dob= LocalDate.parse(value,formatters);
						
						LocalDate now=LocalDate.now();
						long age= ChronoUnit.YEARS.between(dob,now);
						
						if(age>18)
							empList.forEach(x -> x.setDateOfBirth(dob));
						else {
							logger.info("failed because not a valid age");
							throw new InvalidValueException("DirectoryService.NOT_A_VALID_QUERY");	}	
						break;
						default:
							logger.info("failed because not a valid change query");
							throw new InvalidValueException("DirectoryService.NOT_A_VALID_QUERY");
							
				
				}
						
				
				
						return empList;
				}
				catch(Exception ex)
				{
					
					throw new InvalidValueException("DirectoryService.NOT_A_VALID_QUERY");
					
					
				}
				

}
				

}
