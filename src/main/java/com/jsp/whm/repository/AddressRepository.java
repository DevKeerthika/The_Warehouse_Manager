package com.jsp.whm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>
{
	List<Address> findByCity(String city);
}
