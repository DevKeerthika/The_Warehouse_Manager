package com.jsp.whm.entity;

import java.util.ArrayList;
import java.util.List;

import com.jsp.whm.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Storage 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageId;
	private String blockName;
	private String sectionName;
	private List<MaterialTypes> materialTypes;
	private double maxAdditionalWeight;
	private double availableArea;
	private int sellerId;
	
	@ManyToOne
	private WareHouse wareHouse;
	
	@ManyToOne
	private StorageType storageType;
	
	@OneToMany
	private List<Stock> stocks = new ArrayList<Stock>();
	
	
}
