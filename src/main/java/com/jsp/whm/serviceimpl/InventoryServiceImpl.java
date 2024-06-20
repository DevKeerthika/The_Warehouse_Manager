package com.jsp.whm.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Client;
import com.jsp.whm.entity.Inventory;
import com.jsp.whm.entity.Stock;
import com.jsp.whm.entity.Storage;
import com.jsp.whm.exception.ClientNotFoundByIdException;
import com.jsp.whm.exception.InventoryNotFoundByIdException;
import com.jsp.whm.exception.StorageNotFoundByIdException;
import com.jsp.whm.mapper.InventoryMapper;
import com.jsp.whm.repository.ClientRepository;
import com.jsp.whm.repository.InventoryRepository;
import com.jsp.whm.repository.StockRepository;
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
	
	@Autowired
	private StockRepository stockRepository;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(
			@Valid InventoryRequest inventoryRequest, int storageId, int clientId, int quantity) 
	{
		Storage storage = storageRepository.findById(storageId)
				.orElseThrow(() -> new StorageNotFoundByIdException("Failed to fetch stroage for the requested id"));
		
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundByIdException("Failed to fetch client for requested id"));

		Inventory inventory = inventoryRepository.save(inventoryMapper.mapToInventory(inventoryRequest, new Inventory()));
		
		inventory.setClient(client);
		
		inventory.setRestockedAt(LocalDateTime.now());
		
		Stock stock = new Stock();
		
		double wholeWeight = inventory.getWeightInKg()*quantity;

		double area = inventory.getBreadthInMeters()*inventory.getHeightInMeters()*inventory.getLengthInMeters();

		storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight()-wholeWeight);

		storage.setAvailableArea(storage.getAvailableArea()-area);
		storage.setSellerId(inventory.getSellerId());
		
		inventory = inventoryRepository.save(inventory);
		
		storage = storageRepository.save(storage);
		
		clientRepository.save(client);
		
		stock.setInventory(inventory);
		stock.setStorage(storage);
		stock.setQuantity(quantity);
		stock = stockRepository.save(stock);
		
		List<Stock> stocks = new ArrayList<Stock>();
		stocks.add(stock);
				
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<InventoryResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Inventory created")
						.setData(inventoryMapper.mapToInventoryResponse(inventory, stocks)));

			
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


}
