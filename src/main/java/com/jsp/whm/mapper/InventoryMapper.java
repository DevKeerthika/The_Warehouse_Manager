package com.jsp.whm.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Inventory;
import com.jsp.whm.requestdto.InventoryRequest;
import com.jsp.whm.responsedto.InventoryResponse;

@Component
public class InventoryMapper 
{
	public Inventory mapToInventory(InventoryRequest inventoryRequest, Inventory inventory)
	{
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setQuantity(inventoryRequest.getQuantity());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;
		
	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory)
	{
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.lengthInMeters(inventory.getLengthInMeters())
				.breadthInMeters(inventory.getBreadthInMeters())
				.heightInMeters(inventory.getHeightInMeters())
				.weightInKg(inventory.getWeightInKg())
				.quantity(inventory.getQuantity())
				.materialTypes(inventory.getMaterialTypes())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.build();
	}
}
