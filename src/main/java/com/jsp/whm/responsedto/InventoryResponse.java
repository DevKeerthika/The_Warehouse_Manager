package com.jsp.whm.responsedto;

import java.time.LocalDateTime;
import java.util.List;

import com.jsp.whm.entity.Stock;
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
public class InventoryResponse 
{
	private int inventoryId;
    private String productTitle;
    private double lengthInMeters;
    private double breadthInMeters;
    private double heightInMeters;
    private double weightInKg;
    private List<MaterialTypes> materialTypes;
    private LocalDateTime restockedAt;
    private int sellerId;
    
    private List<StockResponse> stocks;
}
