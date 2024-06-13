package com.jsp.whm.responsedto;

import java.util.List;

import com.jsp.whm.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageResponse 
{
	private int storageId;
	private String blockName;
	private String sectionName;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double capacityInKg;
	private List<MaterialTypes> materialTypes;
	private double maxAdditionalWeight;
	private double availableArea;

}
