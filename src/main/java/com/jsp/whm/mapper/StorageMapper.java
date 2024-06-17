package com.jsp.whm.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Storage;
import com.jsp.whm.requestdto.StorageRequest;
import com.jsp.whm.responsedto.StorageResponse;

@Component
public class StorageMapper 
{
	public Storage mapToStorage(StorageRequest storageRequest, Storage storage)
	{
		storage.setBlockName(storageRequest.getBlockName());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		storage.setSectionName(storageRequest.getSectionName());
		return storage;
	}
	
	public StorageResponse mapToStorageResponse(Storage storage)
	{
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.sectionName(storage.getSectionName())
				.materialTypes(storage.getMaterialTypes())
				.maxAdditionalWeight(storage.getMaxAdditionalWeight())
				.availableArea(storage.getAvailableArea())
				.build();
	}
}
