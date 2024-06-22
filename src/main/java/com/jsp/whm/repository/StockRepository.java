package com.jsp.whm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Inventory;
import com.jsp.whm.entity.Stock;
import com.jsp.whm.entity.Storage;

public interface StockRepository extends JpaRepository<Stock, Integer>
{
	List<Stock> findByInventoryAndStorage(Inventory inventory, Storage storage);
}
