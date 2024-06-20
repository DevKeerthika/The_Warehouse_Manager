package com.jsp.whm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Stock 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;
	private double quantity;
	
	@ManyToOne
	private Inventory inventory;
	
	@ManyToOne
	private Storage storage;
}
