package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.BranchEntity;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {
	Optional<BranchEntity> findById(Integer id);
}
