package com.jsp.whm.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.whm.entity.Admin;
import com.jsp.whm.entity.WareHouse;
import com.jsp.whm.enums.AdminType;
import com.jsp.whm.exception.AdminNotFoundByEmailException;
import com.jsp.whm.exception.AdminNotFoundByIdException;
import com.jsp.whm.exception.SuperAdminAlreadyExistException;
import com.jsp.whm.exception.WarehouseNotFoundByIdException;
import com.jsp.whm.mapper.AdminMapper;
import com.jsp.whm.repository.AdminRepository;
import com.jsp.whm.repository.WareHouseRepository;
import com.jsp.whm.requestdto.AdminRequest;
import com.jsp.whm.responsedto.AdminResponse;
import com.jsp.whm.service.AdminService;
import com.jsp.whm.utility.ResponseStructure;


@Service
public class AdminServiceImpl implements AdminService
{

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private WareHouseRepository wareHouseRepository;

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
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest, int wareHouseId) 
	{
		WareHouse wareHouse = wareHouseRepository.findById(wareHouseId)
				.orElseThrow(() -> new WarehouseNotFoundByIdException("Warehouse not found with the requested wareHouseId"));

		Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.ADMIN);
		admin = adminRepository.save(admin);

		wareHouse.setAdmin(admin);
		wareHouseRepository.save(wareHouse);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Admin Created")
						.setData(adminMapper.mapToAdminResponse(admin)));
	}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) 
	{
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return adminRepository.findByEmail(email).map(admin -> {

			admin = adminMapper.mapToAdmin(adminRequest, admin);
			admin = adminRepository.save(admin);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin updated")
							.setData(adminMapper.mapToAdminResponse(admin)));


		}).orElseThrow(() -> new AdminNotFoundByEmailException("Admin not found for the requested email"));
	}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
			int adminId) 
	{
		return adminRepository.findById(adminId).map(admin -> {
			admin = adminMapper.mapToAdmin(adminRequest, admin);
			admin = adminRepository.save(admin);

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin updated by SuperAdmin")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(() -> new AdminNotFoundByIdException("Admin is not found for requested adminId"));
	}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId) 
	{
		return adminRepository.findById(adminId).map(admin -> ResponseEntity
				.status(HttpStatus.FOUND)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Admin found")
						.setData(adminMapper.mapToAdminResponse(admin)))
				).orElseThrow(() -> new AdminNotFoundByIdException("Failed to find the Admin based on the given id"));
	}


	@Override
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAdmins() 
	{
		List<AdminResponse> adminResponses = adminRepository.findAll().stream()
				.filter(admin -> admin.getAdminType() != AdminType.SUPER_ADMIN)
				.map(admin -> adminMapper
				.mapToAdminResponse(admin))
				.toList();
		
		if(adminResponses.isEmpty())
			throw new AdminNotFoundByIdException("Failed to fetch admins");
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<AdminResponse>>()
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Admins found")
						.setData(adminResponses));
		
	}


}
