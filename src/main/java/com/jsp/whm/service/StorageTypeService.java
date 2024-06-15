package com.jsp.whm.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.StorageTypeRequest;
import com.jsp.whm.responsedto.StorageResponse;
import com.jsp.whm.responsedto.StorageTypeResponse;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface StorageTypeService 
{

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorage(@Valid StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
			@Valid StorageTypeRequest storageTypeRequest, int storageTypeId);
	
}
