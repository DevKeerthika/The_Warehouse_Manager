package com.jsp.whm.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.ClientRequest;
import com.jsp.whm.responsedto.ApiKeyResponse;
import com.jsp.whm.responsedto.ClientResponse;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface ClientService 
{

	ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@Valid ClientRequest clientRequest);

	ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@Valid ClientRequest clientRequest, int clientId);
	
}
