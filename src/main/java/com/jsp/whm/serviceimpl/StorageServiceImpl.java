package com.jsp.whm.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Storage;
import com.jsp.whm.entity.StorageType;
import com.jsp.whm.entity.WareHouse;
import com.jsp.whm.exception.StorageNotFoundByIdException;
import com.jsp.whm.exception.StorageTypeNotFoundByIdException;
import com.jsp.whm.exception.WarehouseNotFoundByIdException;
import com.jsp.whm.mapper.StorageMapper;
import com.jsp.whm.repository.StorageRepository;
import com.jsp.whm.repository.StorageTypeRepository;
import com.jsp.whm.repository.WareHouseRepository;
import com.jsp.whm.requestdto.StorageRequest;
import com.jsp.whm.responsedto.StorageResponse;
import com.jsp.whm.service.StorageService;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class StorageServiceImpl implements StorageService
{
	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private WareHouseRepository wareHouseRepository;


	@Autowired
	private StorageMapper storageMapper;

	@Autowired
	private StorageTypeRepository storageTypeRepository;


	@Override
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> addStorage(int wareHouseId, int storageTypeId,
			@Valid StorageRequest storageRequest, int noOfStorageUnits) 
	{
		WareHouse wareHouse = wareHouseRepository.findById(wareHouseId)
				.orElseThrow(() -> new WarehouseNotFoundByIdException("Failed to fetch Warehouse based on id"));

		StorageType storageType =  storageTypeRepository.findById(storageTypeId)
				.orElseThrow(() -> new StorageTypeNotFoundByIdException("Failed to fetch StorageType based on id"));
		
		List<Storage> storages = new ArrayList<Storage>();
		int initialNoOfStorageUnits = noOfStorageUnits;

		while(noOfStorageUnits>0)
		{
			Storage storage = storageMapper.mapToStorage(storageRequest, new Storage());

			storage.setWareHouse(wareHouse); 
			storage.setStorageType(storageType);

			storage.setMaxAdditionalWeight(storageType.getCapacityInKg());
			storage.setAvailableArea(storageType.getBreadthInMeters()*storageType.getHeightInMeters()*storageType.getLengthInMeters());

			storages.add(storage);
			noOfStorageUnits--;
		}

		storageType.setUnitsAvailable(storageType.getUnitsAvailable() + initialNoOfStorageUnits);

		storages = storageRepository.saveAll(storages);

		double totalCapacityInKg = wareHouse.getTotalCapacityInKg();

		wareHouse.setTotalCapacityInKg(storageType.getCapacityInKg()*initialNoOfStorageUnits+totalCapacityInKg);

		wareHouse.setStorages(storages);
		wareHouseRepository.save(wareHouse);
		storageTypeRepository.save(storageType);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseStructure<List<StorageResponse>>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Storage created")
						.setData(storages.stream()
								.map(storage -> storageMapper.mapToStorageResponse(storage))
								.collect(Collectors.toList())));

	}


	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId,
			StorageRequest storageRequest) 
	{
		return storageRepository.findById(storageId).map(storage -> {
			storage = storageMapper.mapToStorage(storageRequest, storage);
			storage = storageRepository.save(storage);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<StorageResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Storage updated")
							.setData(storageMapper.mapToStorageResponse(storage)));
		}).orElseThrow(() -> new StorageNotFoundByIdException("Failed to find the storage based on id"));
	}

	//	@Override
	//	public ResponseEntity<ResponseStructure<StorageResponse>> findFirstStorage(double capacityInKg,
	//			double lengthInMeters, double breadthInMeters, double heightInMeters) 
	//	{
	//		return storageRepository.findFirstByCapacityInKgAndLengthInMetersAndBreadthInMetersAndHeightInMeters(capacityInKg, lengthInMeters, breadthInMeters, heightInMeters)
	//				.map(storage -> ResponseEntity.status(HttpStatus.FOUND)
	//						.body(new ResponseStructure<StorageResponse>()
	//								.setStatus(HttpStatus.FOUND.value())
	//								.setMessage("Based on criteria first storage found")
	//								.setData(storageMapper.mapToStorageResponse(storage)))
	//						).orElseThrow(() -> new StorageNotFoundByIdException("Failed to fetch the first storage based on client requirement"));
	//	}





}
