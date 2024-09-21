package com.jsp.whm.requestdto;

import java.time.LocalDateTime;
import java.util.List;

import com.jsp.whm.enums.MaterialTypes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InventoryRequest 
{
	@NotNull(message = "productTitle cannot be null")
	@NotBlank(message = "productTitle cannot be blank")
	private String productTitle;
	
	@NotNull(message = "lengthInMeters cannot be null")
	@Positive(message = "lengthInMeters must be positive")
	private double lengthInMeters;
	
	@NotNull(message = "breadthInMeters cannot be null")
	@Positive(message = "breadthInMeters must be positive")
	private double breadthInMeters;
	
	@NotNull(message = "heightInMeters cannot be null")
	@Positive(message = "heightInMeters must be positive")
	private double heightInMeters;
	
	@NotNull(message = "weightInKg cannot be null")
	@Positive(message = "weightInKg must be positive")
	private double weightInKg;
	
	
	@NotNull(message = "materialTypes cannot be null")
	@NotEmpty(message = "materialTypes cannot be empty")
	private List<MaterialTypes> materialTypes;
	
	@NotNull(message = "sellerId cannot be null")
	@Positive(message = "sellerId must be positive")
	private int sellerId;
	
}
