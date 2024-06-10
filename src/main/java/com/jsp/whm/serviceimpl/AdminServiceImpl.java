package com.jsp.whm.serviceimpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Admin;
import com.jsp.whm.enums.AdminType;
import com.jsp.whm.enums.Privilege;
import com.jsp.whm.exception.SuperAdminAlreadyExistException;
import com.jsp.whm.mapper.AdminMapper;
import com.jsp.whm.repository.AdminRepository;
import com.jsp.whm.requestdto.AdminRequest;
import com.jsp.whm.responsedto.AdminResponse;
import com.jsp.whm.service.AdminService;
import com.jsp.whm.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements AdminService
{

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) 
	{
		if(adminRepository.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new SuperAdminAlreadyExistException("SuperAdmin already exists in the database");

		Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);

		admin = adminRepository.save(admin);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("SuperAdmin Created")
						.setData(adminMapper.mapToAdminResponse(admin)));



	}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@Valid AdminRequest adminRequest) {
		// TODO Auto-generated method stub
		return null;
	}


}
