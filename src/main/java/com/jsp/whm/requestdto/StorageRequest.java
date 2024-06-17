package com.jsp.whm.requestdto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.whm.enums.MaterialTypes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageRequest 
{
	@NotNull(message = "blockName cannot be null")
	@NotBlank(message = "blockName cannot be blank")
	private String blockName;
	
	@NotNull(message = "sectionName cannot be null")
	@NotBlank(message = "sectionName cannot be blank")
	private String sectionName;
	
			
	@NotNull(message = "materialTypes cannot be null")
	@NotEmpty(message = "materialTypes cannot be empty")
	private List<MaterialTypes> materialTypes;
			
}
