package com.jsp.whm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whm.requestdto.AdminRequest;
import com.jsp.whm.responsedto.AdminResponse;
import com.jsp.whm.service.AdminService;
import com.jsp.whm.utility.ErrorStructure;
import com.jsp.whm.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	@Operation(description = "The endpoint is used to create the "
			+ "SuperAdmin in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "SuperAdmin created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody @Valid AdminRequest adminRequest)
	{
		return adminService.createSuperAdmin(adminRequest);
	}
	
	
	@PreAuthorize("hasAuthority('CREATE_ADMIN')")
	@PostMapping("/warehouses/{wareHouseId}/admins")
	@Operation(description = "The endpoint is used to create the "
			+ "Admin in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Admin created"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})

	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody @Valid AdminRequest adminRequest, @PathVariable int wareHouseId)
	{
		return adminService.createAdmin(adminRequest, wareHouseId);
	}
	
	@PutMapping("/admins")
	@Operation(description = "The endpoint is used to update the "
			+ "Admin in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Admin updated"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})

	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody @Valid AdminRequest adminRequest)
	{
		return adminService.updateAdmin(adminRequest);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
	@PutMapping("/admins/{adminId}")
	@Operation(description = "The endpoint is used to update the "
			+ "Admin by SuperAdmin in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Admin updated by SuperAdmin"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})

	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@RequestBody @Valid AdminRequest adminRequest, @PathVariable int adminId)
	{
		return adminService.updateAdminBySuperAdmin(adminRequest, adminId);
	}
	
	@GetMapping("/admins/{adminId}")
	@Operation(description = "The endpoint is used to find the "
			+ "Admin based on the adminId in the database ", responses = {
					@ApiResponse(responseCode = "201", description = "Admin found"),
					@ApiResponse(responseCode = "400", description = "Invalid input", 
					content = {
							@Content(schema = @Schema(oneOf = ErrorStructure.class))
					})
			})

	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable int adminId)
	{
		return adminService.findAdmin(adminId);
	}
}
