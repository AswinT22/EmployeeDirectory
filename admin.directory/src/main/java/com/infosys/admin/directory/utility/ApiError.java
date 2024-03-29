package com.infosys.admin.directory.utility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiError extends ResponseEntityExceptionHandler {
	

	@Autowired
	ExceptionControllerAdvice expectionHandler;
	

	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @Override
	    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    	 return expectionHandler.handleException(new Exception("Not a Valid URL"),HttpStatus.BAD_REQUEST);
	    }

//    @ExceptionHandler(value={Exception.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<Object> badRequest(Exception e, HttpServletRequest request, HttpServletResponse response) {
//        
//    return expectionHandler.handleException(new Exception("Not a Valid URL"),HttpStatus.BAD_REQUEST);
//    }

	  
   
}  


   
