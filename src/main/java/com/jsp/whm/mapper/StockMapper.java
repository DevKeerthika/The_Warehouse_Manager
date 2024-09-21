package com.jsp.whm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Stock;
import com.jsp.whm.entity.Storage;
import com.jsp.whm.responsedto.StockResponse;

@Component
public class StockMapper 
{
	@Autowired
	private StorageMapper storageMapper;
		
	public StockResponse mapToStockResponse(Stock stock)
	{
		return StockResponse.builder()
				.stockId(stock.getStockId())
				.quantity(stock.getQuantity())
				.storage(storageMapper.mapToStorageResponse(stock.getStorage()))
				.build();
	}
}
