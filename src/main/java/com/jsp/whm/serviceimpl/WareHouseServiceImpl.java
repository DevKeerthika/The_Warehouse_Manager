package com.jsp.whm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.WareHouse;
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

}
