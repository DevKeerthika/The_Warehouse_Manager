package com.jsp.whm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer>
{

}
