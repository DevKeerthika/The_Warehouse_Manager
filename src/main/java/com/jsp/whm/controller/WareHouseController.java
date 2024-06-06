package com.jsp.whm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.WareHouseResponse;
import com.jsp.whm.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController 
{
	
	public String createWareHouse(@RequestBody WareHouseRequest wareHouseRequest)
	{
		return "WareHouse Created";
	}
}
