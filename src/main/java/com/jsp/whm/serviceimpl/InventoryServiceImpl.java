package com.jsp.whm.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Client;
import com.jsp.whm.entity.Inventory;
import com.jsp.whm.entity.Storage;
import com.jsp.whm.exception.ClientNotFoundByIdException;
import com.jsp.whm.exception.StorageNotFoundByIdException;
import com.jsp.whm.mapper.InventoryMapper;
import com.jsp.whm.repository.ClientRepository;
import com.jsp.whm.repository.InventoryRepository;
import com.jsp.whm.repository.StorageRepository;
import com.jsp.whm.requestdto.InventoryRequest;
import com.jsp.whm.responsedto.InventoryResponse;
import com.jsp.whm.service.InventoryService;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class InventoryServiceImpl implements InventoryService
{
	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private StorageRepository storageRepository;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(
			@Valid InventoryRequest inventoryRequest, int storageId, int clientId) 
	{

		Storage storage = storageRepository.findById(storageId)
				.orElseThrow(() -> new StorageNotFoundByIdException("Failed to fetch stroage for the requested id"));

		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundByIdException("Failed to fetch client for requested id"));
		
		Inventory inventory = inventoryMapper.mapToInventory(inventoryRequest, new Inventory());
	
		inventory.setClient(client);
		
		inventory.setRestockedAt(LocalDateTime.now());
		
		storage.getInventories().add(inventory);
		
		double wholeWeight = inventory.getWeightInKg()*inventory.getQuantity();
		
		double area = inventory.getBreadthInMeters()*inventory.getHeightInMeters()*inventory.getLengthInMeters();
		
		storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight()-wholeWeight);
		
		storage.setAvailableArea(storage.getAvailableArea()-area);
		
		storage.setSellerId(inventory.getSellerId());
		
		inventory = inventoryRepository.save(inventory);
		
		storageRepository.save(storage);
		clientRepository.save(client);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<InventoryResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Inventory created")
						.setData(inventoryMapper.mapToInventoryResponse(inventory)));
	}

}
