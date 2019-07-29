package com.infosys.admin.directory.utility;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infosys.admin.directory.dto.EmployeeDTO;

public class EmployeeInformation {

	
	private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   private int count;
	   private List<EmployeeDTO> empList;
	   EmployeeInformation(){
		   
		   timestamp = LocalDateTime.now();
		   
	   }
	   
	   public EmployeeInformation(HttpStatus status, String message, List<EmployeeDTO> empList) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.empList=empList;
	       this.count=empList.size();
	   }
	   public EmployeeInformation(HttpStatus status, String message, EmployeeDTO emp) {
	       this();
	       this.status = status;
	       this.message = message;
	       
	       //Safe Conversion
	       this.empList= Collections.singleton(emp).stream().collect(Collectors.toList());
	       this.count=1;
	   }
	   
	   
	  

	public HttpStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public int getCount() {
		return count;
	}

	public List<EmployeeDTO> getEmpList() {
		return empList;
	}
	
	public ResponseEntity<Object> buildEmployeeInfo() {
	       return new ResponseEntity<>(this, this.getStatus());
	   }
	
	 
}
