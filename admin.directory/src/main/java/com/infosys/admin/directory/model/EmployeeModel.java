package com.infosys.admin.directory.model;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;





public class EmployeeModel {

	
	String name;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	
	LocalDate  dateOfBirth;
	
	String gender;
	
	
	String salary;
	

	String department;
	
public EmployeeModel() {
		
	}
	
	public EmployeeModel(String name, LocalDate dateOfBirth, String gender, String salary, String department) {
		
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.salary = salary;
		this.department = department;
	}


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


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	
}
