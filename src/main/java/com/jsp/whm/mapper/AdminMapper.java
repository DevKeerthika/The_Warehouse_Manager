package com.jsp.whm.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.whm.entity.Admin;
import com.jsp.whm.requestdto.AdminRequest;
import com.jsp.whm.responsedto.AdminResponse;

@Component
public class AdminMapper 
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Admin mapToAdmin(AdminRequest adminRequest, Admin admin)
	{
		admin.setName(adminRequest.getName());
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
		return admin;
	}

	public AdminResponse mapToAdminResponse(Admin admin)
	{
		return AdminResponse.builder()
				.adminId(admin.getAdminId())
				.name(admin.getName())
				.email(admin.getEmail())
				.adminType(admin.getAdminType())
				.build();
	}
}
