package com.jsp.whm.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Inventory;
import com.jsp.whm.entity.Stock;
import com.jsp.whm.requestdto.InventoryRequest;
import com.jsp.whm.responsedto.InventoryResponse;
import com.jsp.whm.responsedto.StockResponse;

@Component
public class InventoryMapper 
{
	@Autowired
	private StockMapper stockMapper;

	public Inventory mapToInventory(InventoryRequest inventoryRequest, Inventory inventory)
	{
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;

	}

	public InventoryResponse mapToInventoryResponse(Inventory inventory)
	{
		return InventoryResponse.builder()
				.inventoryId(inventory.getInventoryId())
				.productTitle(inventory.getProductTitle())
				.lengthInMeters(inventory.getLengthInMeters())
				.breadthInMeters(inventory.getBreadthInMeters())
				.heightInMeters(inventory.getHeightInMeters())
				.weightInKg(inventory.getWeightInKg())
				.materialTypes(inventory.getMaterialTypes())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.build();
	}

	public InventoryResponse mapToInventoryResponse(Inventory inventory, List<Stock> stocks)
	{
		List<StockResponse> responseStock = stocks.stream().map(stock -> stockMapper.mapToStockResponse(stock)).toList();

		InventoryResponse inventoryResponse = mapToInventoryResponse(inventory);
		inventoryResponse.setStocks(responseStock);

		return inventoryResponse;
	}
}
