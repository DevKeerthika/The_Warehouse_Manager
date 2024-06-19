package com.jsp.whm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.requestdto.InventoryRequest;
import com.jsp.whm.responsedto.InventoryResponse;
import com.jsp.whm.service.InventoryService;
import com.jsp.whm.utility.ErrorStructure;
import com.jsp.whm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class InventoryController 
{
	@Autowired
	private InventoryService inventoryService;
	
	
	@PostMapping("/storages/{storageId}/client/{clientId}/inventories")
	@Operation(description = "The endpoint is used to create the "
			+ "Inventory in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Inventory created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(@RequestBody @Valid InventoryRequest inventoryRequest, @PathVariable int storageId, @PathVariable int clientId)
	{
		return inventoryService.createInventory(inventoryRequest, storageId, clientId);
	}
	
	
	@GetMapping("/inventories/{productId}")
	@Operation(description = "The endpoint is used to find the "
			+ "Inventory based on the productId in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Inventory found"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(@PathVariable int productId)
	{
		return inventoryService.findInventory(productId);
	}
	
}
