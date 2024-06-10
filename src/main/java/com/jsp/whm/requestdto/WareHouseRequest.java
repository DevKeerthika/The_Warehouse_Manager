package com.jsp.whm.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WareHouseRequest 
{
	@NotNull(message = "name cannot be null")
	@NotBlank(message = "name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name should contain only alphabets")
	private String name;
}
