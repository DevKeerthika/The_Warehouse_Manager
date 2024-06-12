package com.jsp.whm.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Address;
import com.jsp.whm.requestdto.AddressRequest;
import com.jsp.whm.responsedto.AddressResponse;

@Component
public class AddressMapper 
{
	public Address mapToAddress(AddressRequest addressRequest, Address address)
	{
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setCountry(addressRequest.getCountry());
		address.setLatitude(addressRequest.getLatitude());
		address.setLongitude(addressRequest.getLongitude());
		address.setPincode(addressRequest.getPincode());
		address.setState(addressRequest.getState());
		return address;
	}
	
	public AddressResponse mapToAddressResponse(Address address)
	{
		return AddressResponse.builder()
				.addressId(address.getAddressId())
				.addressLine(address.getAddressLine())
				.city(address.getCity())
				.country(address.getCountry())
				.latitude(address.getLatitude())
				.longitude(address.getLongitude())
				.pincode(address.getPincode())
				.state(address.getState())
				.build();
	}
}
