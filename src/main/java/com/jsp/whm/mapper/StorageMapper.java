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
		storage.setAvailableArea(storageRequest.getBreadthInMeters()*storageRequest.getHeightInMeters()*storageRequest.getLengthInMeters());
		storage.setBlockName(storageRequest.getBlockName());
		storage.setBreadthInMeters(storageRequest.getBreadthInMeters());
		storage.setCapacityInKg(storageRequest.getCapacityInKg());
		storage.setHeightInMeters(storageRequest.getHeightInMeters());
		storage.setLengthInMeters(storageRequest.getLengthInMeters());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		storage.setMaxAdditionalWeight(storageRequest.getCapacityInKg());
		storage.setSectionName(storageRequest.getSectionName());
		return storage;
	}
	
	public StorageResponse mapToStorageResponse(Storage storage)
	{
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.sectionName(storage.getSectionName())
				.lengthInMeters(storage.getLengthInMeters())
				.breadthInMeters(storage.getBreadthInMeters())
				.heightInMeters(storage.getHeightInMeters())
				.capacityInKg(storage.getCapacityInKg())
				.materialTypes(storage.getMaterialTypes())
				.maxAdditionalWeight(storage.getMaxAdditionalWeight())
				.availableArea(storage.getAvailableArea())
				.build();
	}
}
