package com.jsp.whm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whm.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Integer>
{

	Optional<Storage> findFirstByCapacityInKgAndLengthInMetersAndBreadthInMetersAndHeightInMeters(double capacityInKg,
			double lengthInMeters, double breadthInMeters, double heightInMeters);
	
}
