package com.jsp.whm.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Client;
import com.jsp.whm.requestdto.ClientRequest;
import com.jsp.whm.responsedto.ApiKeyResponse;
import com.jsp.whm.responsedto.ClientResponse;

@Component
public class ClientMapper 
{
	public Client mapToClient(ClientRequest clientRequest, Client client)
	{
		client.setBusinessName(clientRequest.getBusinessName());
		client.setContactNumber(clientRequest.getContactNumber());
		client.setEmail(clientRequest.getEmail());
		return client;
	}
	
	public ClientResponse mapToClientResponse(Client client)
	{
		return ClientResponse.builder()
				.clientId(client.getClientId())
				.businessName(client.getBusinessName())
				.email(client.getEmail())
				.contactNumber(client.getContactNumber())
				.build();
	}
	
	public ApiKeyResponse mapToApiKeyResponse(Client client)
	{
		return ApiKeyResponse.builder()
				.apiKey(client.getApiKey()).build();
	}
	
}
