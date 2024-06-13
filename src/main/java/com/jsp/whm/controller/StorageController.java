package com.jsp.whm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.requestdto.StorageRequest;
import com.jsp.whm.responsedto.StorageResponse;
import com.jsp.whm.service.StorageService;
import com.jsp.whm.utility.ErrorStructure;
import com.jsp.whm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class StorageController 
{
	@Autowired
	private StorageService storageService;
	
	@PostMapping("/warehouses/{wareHouseId}/storages")
	@Operation(description = "The endpoint is used to create the "
			+ "Storage in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Storage created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> addStorage(@PathVariable int wareHouseId, @RequestBody @Valid StorageRequest storageRequest, @RequestParam("no_of_storage_units") int noOfStorageUnits)
	{
		return storageService.addStorage(wareHouseId, storageRequest, noOfStorageUnits);
	}
}
