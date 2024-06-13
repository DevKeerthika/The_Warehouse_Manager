package com.jsp.whm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.WareHouse;
import com.jsp.whm.exception.WarehouseNotFoundByIdException;
import com.jsp.whm.mapper.WareHouseMapper;
import com.jsp.whm.repository.WareHouseRepository;
import com.jsp.whm.requestdto.WareHouseRequest;
import com.jsp.whm.responsedto.WareHouseResponse;
import com.jsp.whm.service.WareHouseService;
import com.jsp.whm.utility.ResponseStructure;

@Service
public class WareHouseServiceImpl implements WareHouseService
{
	@Autowired
	private WareHouseRepository wareHouseRepository;

	@Autowired
	private WareHouseMapper wareHouseMapper;

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) 
	{
		WareHouse wareHouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse());
		wareHouseRepository.save(wareHouse);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WareHouseResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("WareHouse Created")
						.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWarehouse(WareHouseRequest wareHouseRequest,
			int wareHouseId) 
	{
		return wareHouseRepository.findById(wareHouseId).map(warehouse -> {
			warehouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, warehouse);
			warehouse = wareHouseRepository.save(warehouse);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<WareHouseResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Warehouse updated")
							.setData(wareHouseMapper.mapToWareHouseResponse(warehouse)));
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("Warehouse not found for requested warehouseId"));
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWarehouse(int wareHouseId) 
	{
		return wareHouseRepository.findById(wareHouseId).map(warehouse -> ResponseEntity
				.status(HttpStatus.FOUND)
				.body(new ResponseStructure<WareHouseResponse>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Warehouse found")
						.setData(wareHouseMapper.mapToWareHouseResponse(warehouse)))
				).orElseThrow(() -> new WarehouseNotFoundByIdException("Failed to find the Warehouse based on id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWarehouses() 
	{
		List<WareHouseResponse> warehouses = wareHouseRepository.findAll().stream()
				.map(warehouse -> wareHouseMapper.mapToWareHouseResponse(warehouse))
				.toList();
		
		if(warehouses.isEmpty())
			throw new WarehouseNotFoundByIdException("Failed to fetch warehouses");
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<WareHouseResponse>>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Warehouses found")
						.setData(warehouses));
	}

}
