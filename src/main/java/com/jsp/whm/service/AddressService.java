package com.jsp.whm.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.AddressRequest;
import com.jsp.whm.responsedto.AddressResponse;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> createAddress(@Valid AddressRequest addressRequest,
			int wareHouseId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest, int addressId);

}
