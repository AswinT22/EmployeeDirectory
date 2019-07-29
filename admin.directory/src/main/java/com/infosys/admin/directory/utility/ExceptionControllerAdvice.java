package com.infosys.admin.directory.utility;


import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infosys.admin.directory.exceptions.InvalidValueException;



/**
 * The Class ExceptionControllerAdvice.
 */

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler{
	
//	@Autowired
//	private MessageSource messageSource;
	/**
	 * Exception handler.
	 *
	 * @param ex the ex
	 * @return the client error information
	 */

	 public ResponseEntity<Object> buildResponseEntity(ClientErrorInformation error) {
	       return new ResponseEntity<>(error, error.getStatus());
	   }
//	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	    @ExceptionHandler({ MethodArgumentNotValidException.class
//	    	/**,HttpMessageNotReadableException.class,HttpRequestMethodNotSupportedException.class**/})
//	    public ResponseEntity<Object> badRequest(HttpServletRequest req, Exception exception) {
//		  return buildResponseEntity(new ClientErrorInformation(HttpStatus.NOT_FOUND, "Not a Valid URL",new Exception("Not a valid Url")));
//	    }
	 
	


@ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
	 public ResponseEntity<Object> handleBadRequests(HttpServletResponse response)  {
		 return buildResponseEntity(new ClientErrorInformation(HttpStatus.NOT_FOUND, "Not a Valid URL",new Exception("Not a valid Url")));
	 }
	 
	 @ExceptionHandler(InvalidValueException.class)
	    public ResponseEntity<Object> handleException(InvalidValueException ex) {

			 
			
			 return buildResponseEntity(new ClientErrorInformation(HttpStatus.ACCEPTED, ex.getMessage(),ex));
		}
	
	
	 public ResponseEntity<Object> handleException(Exception ex,HttpStatus status) {

		 
			
		 return buildResponseEntity(new ClientErrorInformation(status, ex.getMessage(),ex));
	}
	 @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex) {

		 
		
		 return buildResponseEntity(new ClientErrorInformation(HttpStatus.NOT_FOUND, "Not a Valid URL",new Exception("Not a Valid URL")));
	}
	
	
	
	
}

