package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ItemOutputEntity;

@Repository
public interface ItemOuputRepository extends JpaRepository<ItemOutputEntity, Integer> {
	List<ItemOutputEntity> findAll();
	
	Optional<ItemOutputEntity> findById(Integer id);
}
