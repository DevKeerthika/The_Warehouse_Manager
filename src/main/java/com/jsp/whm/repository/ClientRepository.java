package com.jsp.whm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>
{
	Optional<Client> findByEmail(String email);
}
