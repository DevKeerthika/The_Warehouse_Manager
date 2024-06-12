package com.jsp.whm.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressNotFoundByIdException extends RuntimeException
{
	private String message;
	
	@Override
	public String getMessage()
	{
		return message;
	}
}
