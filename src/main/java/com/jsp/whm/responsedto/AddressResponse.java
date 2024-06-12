package com.jsp.whm.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse 
{
	private int addressId;
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String longitude;
	private String latitude;

}
