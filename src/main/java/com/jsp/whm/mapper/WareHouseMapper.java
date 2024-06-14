package com.jsp.whm.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Address;
import com.jsp.whm.entity.WareHouse;
import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.AddressResponse;
import com.jsp.whm.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper 
{
	@Autowired 
	private AddressMapper  addressMapper;

	public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest, WareHouse wareHouse)
	{
		wareHouse.setName(wareHouseRequest.getName());
		return wareHouse;
	}

	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse)
	{
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.totalCapacityInKg(wareHouse.getTotalCapacityInKg())
				.build();
	}
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse, Address address)
	{
		WareHouseResponse response = mapToWareHouseResponse(wareHouse);
		response.setAddressResponse(addressMapper.mapToAddressResponse(address));
		return response;
	}


}
