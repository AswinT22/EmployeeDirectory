package com.infosys.directory.dto;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infosys.directory.entity.Employee;
import com.infosys.directory.model.EmployeeModel;

public class EmployeeDTO {
	public EmployeeDTO() {}
	
	public EmployeeDTO(String name, LocalDate dateOfBirth, String gender, BigInteger salary, String department) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.salary = salary;
		this.department = department;
	}

	String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	LocalDate  dateOfBirth;
	
	String gender;
	
	BigInteger salary;
	
	String department;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigInteger getSalary() {
		return salary;
	}

	public void setSalary(BigInteger salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public long getAgeMonths() {
		
		LocalDate now=LocalDate.now();
		
		 
		return ChronoUnit.MONTHS.between(getDateOfBirth(),now);
	}
	
		public long getAge() {
		
		LocalDate now=LocalDate.now();
		
		return ChronoUnit.YEARS.between(getDateOfBirth(),now);
	}
	
	
	@Override
	public String toString() {
		return "EmployeeDTO [Name=" + name + ", DateOfBirth=" + dateOfBirth + ", Gender=" + gender + ", Salary=" + salary
				+ ", Department=" + department + "Age ="+getAge()+"AgeMonths="+getAgeMonths()+"]";
	}
	
	
	
		// Converts Entity into DTO
		public static EmployeeDTO valueOf(Employee employee) {

			EmployeeDTO employeeDTO=new EmployeeDTO();
			
			
			employeeDTO.setName(employee.getName());
			//LocalDate dob=employee.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			employeeDTO.setDateOfBirth(employee.getDateOfBirth());
			employeeDTO.setGender(employee.getGender());
			employeeDTO.setSalary(employee.getSalary());
			employeeDTO.setDepartment(employee.getDepartment());
			
			return employeeDTO;
		}
		
		public static EmployeeDTO modelOf(EmployeeModel employee) {

			EmployeeDTO employeeDTO=new EmployeeDTO();
			
			
			employeeDTO.setName(employee.getName());
			employeeDTO.setDateOfBirth(employee.getDateOfBirth());
			employeeDTO.setGender(employee.getGender());
			employeeDTO.setSalary(new BigInteger(employee.getSalary()));
			employeeDTO.setDepartment(employee.getDepartment().toUpperCase());
			
			return employeeDTO;
		}
		
		

		// Converts DTO into Entity
		public Employee createEntity() {
			Employee employee = new Employee();
			
			employee.setName(this.getName());
			//Date dob=Date.from(getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant());
			employee.setDateOfBirth(this.getDateOfBirth());
			employee.setGender(this.getGender());
			employee.setSalary(this.getSalary());
			employee.setDepartment(this.getDepartment().toUpperCase());
			
			return employee;
		}
	
	

}
