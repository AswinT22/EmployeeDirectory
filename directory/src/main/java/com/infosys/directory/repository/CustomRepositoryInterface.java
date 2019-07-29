package com.infosys.directory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.directory.entity.Employee;
import com.infosys.directory.entity.EmployeeId;


public interface CustomRepositoryInterface extends JpaRepository<Employee, EmployeeId> {
	
	
	public List<Employee> getData(String metric, String value);
	

}
