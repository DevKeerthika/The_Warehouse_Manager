package com.jsp.whm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>
{
	Client findByEmail(String email);
}
