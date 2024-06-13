package com.jsp.whm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.StorageRequest;
import com.jsp.whm.responsedto.StorageResponse;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface StorageService 
{

	ResponseEntity<ResponseStructure<List<StorageResponse>>> addStorage(int wareHouseId, @Valid StorageRequest storageRequest,
			int noOfStorageUnits);

}
