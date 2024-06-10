package com.jsp.whm.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.WareHouseResponse;
import com.jsp.whm.utility.ResponseStructure;

public interface WareHouseService 
{

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest);

}
