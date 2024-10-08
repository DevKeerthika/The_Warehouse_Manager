package com.jsp.whm.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jsp.whm.enums.MaterialTypes;

import jakarta.persistence.Entity;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryId;
    private String productTitle;
    private double lengthInMeters;
    private double breadthInMeters;
    private double heightInMeters;
    private double weightInKg;
    private List<MaterialTypes> materialTypes;
    private LocalDate restockedAt;
    private int sellerId;
    
    @ManyToOne
    private Client client;
    
    @OneToMany(mappedBy = "inventory")
    private List<Stock> stocks = new ArrayList<Stock>();
    
   
}
