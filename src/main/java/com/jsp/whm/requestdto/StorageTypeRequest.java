package com.jsp.whm.requestdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageTypeRequest {
	
	@NotNull(message = "lengthInMeters cannot be null")
	@Positive(message = "lengthInMeters must be positive")
	private double lengthInMeters;
	
	@NotNull(message = "breadthInMeters cannot be null")
	@Positive(message = "breadthInMeters must be positive")
	private double breadthInMeters;
	
	@NotNull(message = "heightInMeters cannot be null")
	@Positive(message = "heightInMeters must be positive")
	private double heightInMeters;
	
	@NotNull(message = "capacityInKg cannot be null")
	@Positive(message = "capacityInKg must be positive")
	private double capacityInKg;
	

}
