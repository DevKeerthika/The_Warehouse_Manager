package com.jsp.whm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.repository.WareHouseRepository;
import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.WareHouseResponse;
import com.jsp.whm.service.WareHouseService;
import com.jsp.whm.utility.ErrorStructure;
import com.jsp.whm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController 
{
	@Autowired
	private WareHouseService wareHouseService;
	
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
	@PostMapping("/warehouses")
	@Operation(description = "The endpoint is used to create the "
			+ "Warehouse in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Warehouse created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})

	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(@RequestBody @Valid WareHouseRequest wareHouseRequest)
	{
		return wareHouseService.createWareHouse(wareHouseRequest);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
	@PutMapping("/warehouses/{wareHouseId}")
	@Operation(description = "The endpoint is used to update the "
			+ "Warehouse in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Warehouse updated"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})

	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWarehouse(@RequestBody @Valid WareHouseRequest wareHouseRequest, @PathVariable int wareHouseId)
	{
		return wareHouseService.updateWarehouse(wareHouseRequest, wareHouseId);
	}
	
	@GetMapping("/warehouses/{wareHouseId}")
	@Operation(description = "The endpoint is used to find the "
			+ "Warehouse based on id in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Warehouse found"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})

	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(@PathVariable int wareHouseId)
	{
		return wareHouseService.findWarehouse(wareHouseId);
	}
	
	
	@GetMapping("/warehouses")
	@Operation(description = "The endpoint is used to find all the "
			+ "Warehouses in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Warehouses found"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehouses()
	{
		return wareHouseService.findWarehouses();
	}
	
	
	@GetMapping("/cities/{city}/warehouses")
	@Operation(description = "The endpoint is used to find all the "
			+ "Warehouses based on city in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Warehouses found"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehousesByCity(@PathVariable String city)
	{
		return wareHouseService.findWarehousesByCity(city);
	}
}
