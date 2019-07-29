package com.infosys.directory.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.directory.entity.Employee;
import com.infosys.directory.entity.EmployeeId;


public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId>{
	
	
	List<Employee> findByDepartment(String department);
	List<Employee> findByDepartmentAndSalaryGreaterThan(String department,BigInteger salary);
	

}
