package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.SupplierEntity;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
	
	Optional<SupplierEntity> findByName(String name);
	
}
