package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.StaffEntity;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {
	Optional<StaffEntity> findByUsername(String username);
}
