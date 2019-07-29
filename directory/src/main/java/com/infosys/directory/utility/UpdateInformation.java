package com.infosys.directory.utility;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infosys.directory.dto.EmployeeDTO;
import com.infosys.directory.model.QueryModel;

public class UpdateInformation {


		
		private HttpStatus status;
		   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
		   private LocalDateTime timestamp;
		   private String message;
		   private int count;
		   private List<QueryModel> queryList;
		   UpdateInformation(){
			   
			   timestamp = LocalDateTime.now();
			   
		   }
		   
		   public UpdateInformation(HttpStatus status, String message, List<QueryModel> queryList) {
		       this();
		       this.status = status;
		       this.message = message;
		       this.queryList=queryList;
		       this.count=queryList.size();
		   }
		   public UpdateInformation(HttpStatus status, String message, QueryModel query) {
		       this();
		       this.status = status;
		       this.message = message;
		       
		       //Safe Conversion
		       this.queryList= Collections.singleton(query).stream().collect(Collectors.toList());
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

		public List<QueryModel> getQueryList() {
			return queryList;
		}
		
		public ResponseEntity<Object> buildUpdateInfo() {
		       return new ResponseEntity<>(this, this.getStatus());
		   }
		
		 
	}
