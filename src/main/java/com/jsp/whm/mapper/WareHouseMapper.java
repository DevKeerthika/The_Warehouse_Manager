package com.jsp.whm.mapper;


import org.springframework.stereotype.Component;

import com.jsp.whm.entity.WareHouse;
import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper 
{
	
	public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest, WareHouse wareHouse)
	{
		wareHouse.setName(wareHouseRequest.getName());
		wareHouse.setTotalCapacity(0);
		return wareHouse;
	}
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse)
	{
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.totalCapacity(wareHouse.getTotalCapacity())
				.build();
	}
}
