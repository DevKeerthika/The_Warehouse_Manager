package com.jsp.whm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>
{

}
