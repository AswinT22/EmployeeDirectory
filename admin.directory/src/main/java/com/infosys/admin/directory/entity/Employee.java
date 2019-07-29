package com.infosys.admin.directory.entity;
import java.math.BigInteger;
import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;




@Entity
@Table(name = "employee")
@IdClass(EmployeeId.class)
@Valid
public class Employee {
	
	
	@GeneratedValue
	@Id
	int id;
	
	@Id
	@NotNull
	@NotBlank
	@Length(min=1,max=80)
	String name;
	
	@Column(name="dob")
	
	LocalDate  dateOfBirth;
	
	@Column
	@NotNull
	@NotBlank
	@Length(min=1,max=80)
	String gender;
	
	
	@Column
	@NotNull
	@Min(value=1)
	BigInteger salary;
	
	
	@Column
	@NotNull
	@NotBlank
	@Length(min=1,max=7)
	String department;


	public int getId() {
		return id;
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
	
	

}
