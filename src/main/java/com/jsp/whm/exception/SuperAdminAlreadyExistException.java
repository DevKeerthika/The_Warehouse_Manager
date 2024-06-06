package com.jsp.whm.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SuperAdminAlreadyExistException extends RuntimeException
{
	private String message;

	public SuperAdminAlreadyExistException(String message) 
	{
		this.message = message;
	}
	
	
	
	
}
