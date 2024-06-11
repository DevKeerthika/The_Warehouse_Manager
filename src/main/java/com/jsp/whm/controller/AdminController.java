package com.jsp.whm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.requestdto.AdminRequest;
import com.jsp.whm.responsedto.AdminResponse;
import com.jsp.whm.service.AdminService;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody @Valid AdminRequest adminRequest)
	{
		return adminService.createSuperAdmin(adminRequest);
	}
	
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PostMapping("/warehouses/{wareHouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody @Valid AdminRequest adminRequest, @PathVariable int wareHouseId)
	{
		return adminService.createAdmin(adminRequest, wareHouseId);
	}
	
	@PutMapping("/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody @Valid AdminRequest adminRequest)
	{
		return adminService.updateAdmin(adminRequest);
	}
	
}
