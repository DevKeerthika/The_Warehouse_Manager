package com.jsp.whm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.requestdto.StorageTypeRequest;
import com.jsp.whm.responsedto.StorageResponse;
import com.jsp.whm.responsedto.StorageTypeResponse;
import com.jsp.whm.service.StorageTypeService;
import com.jsp.whm.utility.ErrorStructure;
import com.jsp.whm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class StorageTypeController 
{
	@Autowired
	private StorageTypeService storageTypeService;
	
	@PostMapping("/storagetypes")
	@Operation(description = "The endpoint is used to create the "
			+ "StorageType in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "StorageType created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(@RequestBody @Valid StorageTypeRequest storageTypeRequest)
	{
		return storageTypeService.createStorage(storageTypeRequest);
	}
	
	
	@PutMapping("/storagetypes/{storageTypeId}")
	@Operation(description = "The endpoint is used to update the "
			+ "StorageType in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "StorageType updated"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(@RequestBody @Valid StorageTypeRequest storageTypeRequest, @PathVariable int storageTypeId)
	{
		return storageTypeService.updateStorageType(storageTypeRequest, storageTypeId);
	}
}
