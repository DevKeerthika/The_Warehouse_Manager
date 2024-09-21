package com.jsp.whm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Integer>
{
	
}
