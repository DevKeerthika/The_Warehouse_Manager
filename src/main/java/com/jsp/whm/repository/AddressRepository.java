package com.jsp.whm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>
{

}
