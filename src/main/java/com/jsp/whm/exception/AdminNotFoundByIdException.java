package com.jsp.whm.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdminNotFoundByIdException extends RuntimeException
{
	private String message;
	
	public String getMessage()
	{
		return message;
	}
}
