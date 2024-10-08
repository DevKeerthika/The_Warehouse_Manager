package com.jsp.whm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Address;
import com.jsp.whm.entity.WareHouse;
import com.jsp.whm.exception.AddressNotFoundByIdException;
import com.jsp.whm.exception.WarehouseNotFoundByIdException;
import com.jsp.whm.mapper.AddressMapper;
import com.jsp.whm.repository.AddressRepository;
import com.jsp.whm.repository.WareHouseRepository;
import com.jsp.whm.requestdto.AddressRequest;
import com.jsp.whm.responsedto.AddressResponse;
import com.jsp.whm.service.AddressService;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AddressServiceImpl implements AddressService
{

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private WareHouseRepository wareHouseRepository;

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(@Valid AddressRequest addressRequest,
			int wareHouseId) 
	{
		WareHouse wareHouse = wareHouseRepository.findById(wareHouseId)
				.orElseThrow(() -> new WarehouseNotFoundByIdException("Failed to find the warehouse based on id"));
		
		Address address = addressMapper.mapToAddress(addressRequest, new Address());
		address.setWareHouse(wareHouse);
		address = addressRepository.save(address);
		
		wareHouseRepository.save(wareHouse);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AddressResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Address created")
						.setData(addressMapper.mapToAddressResponse(address)));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest,
			int addressId)
	{
		return addressRepository.findById(addressId).map(address -> {
			address = addressMapper.mapToAddress(addressRequest, address);
			address = addressRepository.save(address);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<AddressResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Address updated")
							.setData(addressMapper.mapToAddressResponse(address)));
		}).orElseThrow(() -> new AddressNotFoundByIdException("Failed to update address for given id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId) 
	{
		return addressRepository.findById(addressId).map(address -> ResponseEntity
				.status(HttpStatus.FOUND)
				.body(new ResponseStructure<AddressResponse>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Address found")
						.setData(addressMapper.mapToAddressResponse(address)))
				).orElseThrow(() -> new AddressNotFoundByIdException("Failed to find the address based on id"));
	}

	

}
