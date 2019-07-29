package com.infosys.directory.utility;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClientErrorInformation {
	private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   private String debugMessage;
	   private List<ClientSubErrorInfo> subErrors;
	   
	   
	   
	   
	   

	   public HttpStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public List<ClientSubErrorInfo> getSubErrors() {
		return subErrors;
	}

	private ClientErrorInformation() {
	       timestamp = LocalDateTime.now();
	   }

	   ClientErrorInformation(HttpStatus status) {
	       this();
	       this.status = status;
	   }

	   ClientErrorInformation(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = ex.getMessage();
	       this.debugMessage = ex.getMessage();
	   }

	   public ClientErrorInformation(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = ex.getMessage();
	       this.debugMessage = ex.getMessage();
	   }
	   public ResponseEntity<Object> buildErrorInfo() {
	       return new ResponseEntity<>(this, this.getStatus());
	   }
}
