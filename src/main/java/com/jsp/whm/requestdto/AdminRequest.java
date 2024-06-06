package com.jsp.whm.requestdto;

import java.util.List;

import com.jsp.whm.enums.AdminType;
import com.jsp.whm.enums.Privilege;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest 
{
	@NotNull(message = "name cannot be null")
	@NotBlank(message = "name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name should contain only alphabets")
	private String name;
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be ending with @gmail.com")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid Password")
	private String password;
}
