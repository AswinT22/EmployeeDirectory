package com.infosys.admin.directory.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.admin.directory.entity.Employee;
import com.infosys.admin.directory.entity.EmployeeId;


public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId>{
	
	
	List<Employee> findByDepartment(String department);
	List<Employee> findByDepartmentAndSalaryGreaterThan(String department,BigInteger salary);
	

}
