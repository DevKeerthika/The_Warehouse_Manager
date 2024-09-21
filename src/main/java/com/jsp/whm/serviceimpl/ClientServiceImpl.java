package com.jsp.whm.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Client;
import com.jsp.whm.exception.ClientNotFoundByIdException;
import com.jsp.whm.mapper.ClientMapper;
import com.jsp.whm.repository.ClientRepository;
import com.jsp.whm.requestdto.ClientRequest;
import com.jsp.whm.responsedto.ApiKeyResponse;
import com.jsp.whm.responsedto.ClientResponse;
import com.jsp.whm.service.ClientService;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ClientServiceImpl implements ClientService
{
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientMapper clientMapper;

	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@Valid ClientRequest clientRequest) 
	{
		Client client = clientRepository.save(clientMapper.mapToClient(clientRequest, new Client()));
		String apiKey = UUID.randomUUID().toString();
		client.setApiKey(apiKey);
		clientRepository.save(client);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<ApiKeyResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Client created, store the apiKey in secure place and use it for subsequent requests")
						.setData(clientMapper.mapToApiKeyResponse(client)));

	}

	@Override
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@Valid ClientRequest clientRequest,
			int clientId) 
	{
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundByIdException("Failed to fetch client based on id"));
		
		client = clientMapper.mapToClient(clientRequest, client);
		clientRepository.save(client);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<ClientResponse>()
						.setStatus(HttpStatus.OK.value())
						.setMessage("Client updated")
						.setData(clientMapper.mapToClientResponse(client)));
	}

}
