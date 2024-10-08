package com.jsp.whm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.InventoryRequest;
import com.jsp.whm.responsedto.InventoryResponse;
import com.jsp.whm.responsedto.StockResponse;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface InventoryService 
{

	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(@Valid InventoryRequest inventoryRequest, int storageId, int clientId, int quantity);

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories();

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(@Valid InventoryRequest inventoryRequest,
			int inventoryId, int storageId);

	ResponseEntity<ResponseStructure<StockResponse>> updateStockQuantity(int storageId, int inventoryId,
			int quantity);

}
