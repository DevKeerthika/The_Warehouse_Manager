package com.jsp.whm.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whm.requestdto.AdminRequest;
import com.jsp.whm.responsedto.AdminResponse;
import com.jsp.whm.utility.ResponseStructure;


public interface AdminService 
{
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest);

	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest, int wareHouseId);

	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);

	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
			int adminId);

}
