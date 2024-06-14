package com.jsp.whm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.requestdto.ClientRequest;
import com.jsp.whm.responsedto.ApiKeyResponse;
import com.jsp.whm.responsedto.ClientResponse;
import com.jsp.whm.service.ClientService;
import com.jsp.whm.utility.ErrorStructure;
import com.jsp.whm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ClientController 
{
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping("/clients")
	@Operation(description = "The endpoint is used to register the "
			+ "Client in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Client created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody @Valid ClientRequest clientRequest)
	{
		return clientService.registerClient(clientRequest);
	}
	
	@PutMapping("/clients/{clientId}")
	@Operation(description = "The endpoint is used to update the "
			+ "Client in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Client updated"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@RequestBody @Valid ClientRequest clientRequest, @PathVariable int clientId)
	{
		return clientService.updateClient(clientRequest, clientId);
	}
}
