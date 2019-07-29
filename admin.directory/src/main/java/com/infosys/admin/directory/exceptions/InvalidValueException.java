package com.infosys.admin.directory.exceptions;

public class InvalidValueException  extends Exception{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * This Exception is thrown from Admin Service class if the given input invalid
	 * 
	 * @author aswin
	 *
	 */
	public InvalidValueException(String message)
	{
		super(message);
	}


}
