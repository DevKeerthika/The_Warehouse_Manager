package com.jsp.whm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Admin;
import com.jsp.whm.enums.AdminType;

public interface AdminRepository extends JpaRepository<Admin, Integer>
{
	public boolean existsByAdminType(AdminType adminType);

	public Optional<Admin> findByEmail(String username);
	
	public List<Admin> findAllByAdminType(AdminType adminType);
}
