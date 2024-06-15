package com.jsp.whm.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whm.entity.StorageType;
import com.jsp.whm.requestdto.StorageTypeRequest;
import com.jsp.whm.responsedto.StorageTypeResponse;

@Component
public class StorageTypeMapper {
	
	public StorageType mapToStorageType(StorageTypeRequest storageTypeRequest, StorageType storageType)
	{
		storageType.setBreadthInMeters(storageTypeRequest.getBreadthInMeters());
		storageType.setCapacityInKg(storageTypeRequest.getCapacityInKg());
		storageType.setHeightInMeters(storageTypeRequest.getHeightInMeters());
		storageType.setLengthInMeters(storageTypeRequest.getLengthInMeters());
		return storageType;
	}
	
	public StorageTypeResponse mapToStorageTypeResponse(StorageType storageType)
	{
		return StorageTypeResponse.builder()
				.storageTypeId(storageType.getStorageTypeId())
				.lengthInMeters(storageType.getLengthInMeters())
				.breadthInMeters(storageType.getBreadthInMeters())
				.heightInMeters(storageType.getHeightInMeters())
				.capacityInKg(storageType.getCapacityInKg())
				.unitsAvailable(storageType.getUnitsAvailable())
				.build();
	}

}
