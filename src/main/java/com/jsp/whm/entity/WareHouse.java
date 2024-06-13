package com.jsp.whm.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WareHouse 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wareHouseId;
	private String name;
	private double totalCapacityInKg;
	
	@OneToOne
	private Admin admin;
	
	@OneToMany(mappedBy = "wareHouse")
	private List<Storage> storages = new ArrayList<Storage>();

}
