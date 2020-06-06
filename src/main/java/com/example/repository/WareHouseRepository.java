package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.WareHouseEntity;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouseEntity, Integer> {
	Optional<WareHouseEntity> findById(Integer id);
}
