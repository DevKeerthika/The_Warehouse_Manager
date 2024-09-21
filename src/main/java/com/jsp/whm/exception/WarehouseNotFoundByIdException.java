package com.jsp.whm.exception;

import lombok.Getter;

@Getter
public class WarehouseNotFoundByIdException extends RuntimeException
{
	private String message;

	public WarehouseNotFoundByIdException(String message) 
	{
		this.message = message;
	}
	
	
}
