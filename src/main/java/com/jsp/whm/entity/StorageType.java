package com.jsp.whm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class StorageType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageTypeId;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double capacityInKg;
	private double unitsAvailable;

}
