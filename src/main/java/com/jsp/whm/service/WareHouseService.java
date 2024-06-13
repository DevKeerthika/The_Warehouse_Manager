package com.jsp.whm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.WareHouseResponse;
import com.jsp.whm.utility.ResponseStructure;

public interface WareHouseService 
{

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest);

	ResponseEntity<ResponseStructure<WareHouseResponse>> updateWarehouse(WareHouseRequest wareHouseRequest,
			int wareHouseId);

	ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(int wareHouseId);

	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehouses();

}
