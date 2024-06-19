package com.jsp.whm.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Client;
import com.jsp.whm.entity.Inventory;
import com.jsp.whm.entity.Storage;
import com.jsp.whm.exception.ClientNotFoundByIdException;
import com.jsp.whm.exception.InventoryNotFoundByIdException;
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

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId) 
	{
		return inventoryRepository.findById(productId)
				.map(inventory -> ResponseEntity
						.status(HttpStatus.FOUND)
						.body(new ResponseStructure<InventoryResponse>()
								.setStatus(HttpStatus.FOUND.value())
								.setMessage("Inventory found")
								.setData(inventoryMapper.mapToInventoryResponse(inventory)))
						).orElseThrow(() -> new InventoryNotFoundByIdException("Failed to find Inventory based on given id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories() 
	{
		List<InventoryResponse> inventoryResponses = inventoryRepository.findAll()
				.stream()
				.map(inventoryMapper::mapToInventoryResponse)
				.toList();

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<InventoryResponse>>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Inventories found")
						.setData(inventoryResponses));
	}

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
			@Valid InventoryRequest inventoryRequest, int productId) 
	{
		return inventoryRepository.findById(productId).map(inventory -> {
			inventory = inventoryMapper.mapToInventory(inventoryRequest, inventory);

			if(inventoryRequest.getQuantity() != inventory.getQuantity())
				inventory.setRestockedAt(LocalDateTime.now());

			inventory.setQuantity(inventoryRequest.getQuantity());
			inventory = inventoryRepository.save(inventory);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<InventoryResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Inventory updated")
							.setData(inventoryMapper.mapToInventoryResponse(inventory)));
		}).orElseThrow(() -> new InventoryNotFoundByIdException("Failed to update the inventory based on id"));
	}


}
