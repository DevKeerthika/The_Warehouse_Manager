package com.jsp.whm.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.InventoryRequest;
import com.jsp.whm.responsedto.InventoryResponse;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface InventoryService 
{

	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(@Valid InventoryRequest inventoryRequest, int storageId, int clientId);

}
