package com.jsp.whm.utility;

public class ResponseStructure<T> 
{
	private int status;
	private String message;
	private T data;
	
	
	
	public int getStatus() {
		return status;
	}
	public ResponseStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public T getData() {
		return data;
	}
	
	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}
	
		
	
}
