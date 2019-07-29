package com.infosys.directory.exceptions;

public class InvalidValueException  extends Exception{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * This Exception is thrown from Directory Service class if the given input invalid
	 * 
	 * @author aswin
	 *
	 */
	public InvalidValueException(String message)
	{
		super(message);
	}


}
