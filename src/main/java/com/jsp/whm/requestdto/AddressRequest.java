package com.jsp.whm.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest 
{
	@NotNull(message = "addressLine cannot be null")
	@NotBlank(message = "addressLine cannot be blank")
	private String addressLine;
	
	@NotNull(message = "city cannot be null")
	@NotBlank(message = "city cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "city should contain only alphabets")
	private String city;
	
	@NotNull(message = "state cannot be null")
	@NotBlank(message = "state cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "state should contain only alphabets")
	private String state;
	
	@NotNull(message = "country cannot be null")
	@NotBlank(message = "country cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "country should contain only alphabets")
	private String country;
	
	@NotNull(message = "pincode cannot be null")
	@NotBlank(message = "pincode cannot be blank")
	@Pattern(regexp = "^\\d{6}$", message = "pincode should contain only six digits")
	private String pincode;
	
	@NotNull(message = "longitude cannot be null")
	@NotBlank(message = "longitude cannot be blank")
	private String longitude;
	
	@NotNull(message = "latitude cannot be null")
	@NotBlank(message = "latitude cannot be blank")
	private String latitude;

}
