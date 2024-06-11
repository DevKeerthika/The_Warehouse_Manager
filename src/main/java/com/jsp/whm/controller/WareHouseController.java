package com.jsp.whm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.repository.WareHouseRepository;
import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.WareHouseResponse;
import com.jsp.whm.service.WareHouseService;
import com.jsp.whm.utility.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController 
{
	@Autowired
	private WareHouseService wareHouseService;
	
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
	@PostMapping("/warehouses")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(@RequestBody WareHouseRequest wareHouseRequest)
	{
		return wareHouseService.createWareHouse(wareHouseRequest);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
	@PutMapping("/warehouses/{wareHouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWarehouse(@RequestBody WareHouseRequest wareHouseRequest, @PathVariable int wareHouseId)
	{
		return wareHouseService.updateWarehouse(wareHouseRequest, wareHouseId);
	}
}
