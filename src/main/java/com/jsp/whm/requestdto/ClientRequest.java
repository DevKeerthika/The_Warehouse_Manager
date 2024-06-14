package com.jsp.whm.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest 
{
	@NotNull(message = "businessName cannot be null")
	@NotBlank(message = "businessName cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "businessName should contain only alphabets")
	private String businessName;
	
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be ending with @gmail.com")
	private String email;
	
	@Pattern(regexp = "^\\d{10}$", message = "contactNumber should contain ten digits")
	private String contactNumber;
	
}
