package com.jsp.whm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.StorageType;
import com.jsp.whm.mapper.StorageMapper;
import com.jsp.whm.mapper.StorageTypeMapper;
import com.jsp.whm.repository.StorageTypeRepository;
import com.jsp.whm.requestdto.StorageTypeRequest;
import com.jsp.whm.responsedto.StorageResponse;
import com.jsp.whm.responsedto.StorageTypeResponse;
import com.jsp.whm.service.StorageTypeService;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class StorageTypeServiceImpl implements StorageTypeService
{
	
	@Autowired
	private StorageTypeRepository storageTypeRepository;
	
	@Autowired
	private StorageTypeMapper storageTypeMapper;

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorage(
			@Valid StorageTypeRequest storageTypeRequest) 
	{
		StorageType storageType = storageTypeMapper.mapToStorageType(storageTypeRequest, new StorageType());
		storageType.setUnitsAvailable(0);
		storageTypeRepository.save(storageType);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StorageTypeResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("StorageType created")
						.setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
	}

}
