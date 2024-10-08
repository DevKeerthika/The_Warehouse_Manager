package com.jsp.whm.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.whm.exception.AddressNotFoundByCityException;
import com.jsp.whm.exception.AddressNotFoundByIdException;
import com.jsp.whm.exception.AdminNotFoundByEmailException;
import com.jsp.whm.exception.AdminNotFoundByIdException;
import com.jsp.whm.exception.IllegalOperationException;
import com.jsp.whm.exception.InventoryNotFoundByIdException;
import com.jsp.whm.exception.StorageNotFoundByIdException;
import com.jsp.whm.exception.SuperAdminAlreadyExistException;
import com.jsp.whm.exception.UsernameNotFoundException;
import com.jsp.whm.exception.WarehouseNotFoundByIdException;

@RestControllerAdvice
public class ApplicationHandler 
{
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String message, String rootCause)
	{
		return ResponseEntity
				.status(status)
				.body(new ErrorStructure<String>()
						.setStatus(status.value())
						.setErrorMessage(message)
						.setRootCause(rootCause));
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleSuperAdminAlreadyExistException(SuperAdminAlreadyExistException e)
	{
		return errorResponse(HttpStatus.FORBIDDEN, e.getMessage(), "Only one Super Admin Allowed");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAdminNotFoundByEmailException(AdminNotFoundByEmailException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Admin not found by given email");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAdminNotFoundByIdException(AdminNotFoundByIdException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Admin not found by given id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleWarehouseNotFoundByIdException(WarehouseNotFoundByIdException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Warehouse not found by given id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAddressNotFoundByIdException(AddressNotFoundByIdException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Address not found by given id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleStorageNotFoundByIdException(StorageNotFoundByIdException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Storage not found by given id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleAddressNotFoundByCityException(AddressNotFoundByCityException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Address not found by given city");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUsernameNotFoundException(UsernameNotFoundException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Username not found");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalOperationException(IllegalOperationException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Illegal operation");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleInventoryNotFoundByIdException(InventoryNotFoundByIdException e)
	{
		return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), "Inventory not found");
	}

	

	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException e)
	{
		List<ObjectError> errors = e.getAllErrors();
		
		Map<String, String> allErrors = new HashMap<String, String>();
		
		errors.forEach(error -> {
			FieldError fieldError = (FieldError) error;
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			allErrors.put(field, message);
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorStructure<Map<String, String>>()
						.setStatus(HttpStatus.BAD_REQUEST.value())
						.setErrorMessage("Invalid input")
						.setRootCause(allErrors));
	}
}
	