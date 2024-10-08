package com.jsp.whm.controller;

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

import com.jsp.whm.requestdto.AddressRequest;
import com.jsp.whm.responsedto.AddressResponse;
import com.jsp.whm.service.AddressService;
import com.jsp.whm.utility.ErrorStructure;
import com.jsp.whm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AddressController 
{
	@Autowired
	private AddressService addressService;
	
	@PreAuthorize("hasAuthority('CREATE_ADDRESS')")
	@PostMapping("/warehouses/{wareHouseId}/addresses")
	@Operation(description = "The endpoint is used to create the "
			+ "Address in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Address created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(@RequestBody @Valid AddressRequest addressRequest, @PathVariable int wareHouseId)
	{
		return addressService.createAddress(addressRequest, wareHouseId);
	}
	
	
	@PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
	@PutMapping("/addresses/{addressId}")
	@Operation(description = "The endpoint is used to update the "
			+ "Address in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Address updated"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@RequestBody @Valid AddressRequest addressRequest, @PathVariable int addressId)
	{
		return addressService.updateAddress(addressRequest, addressId);
	}
	
	@GetMapping("/addresses/{addressId}")
	@Operation(description = "The endpoint is used to find the "
			+ "Address in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Address found"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(@PathVariable int addressId)
	{
		return addressService.findAddress(addressId);
	}
}
