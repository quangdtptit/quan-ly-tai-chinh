package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
	Optional<ItemEntity> findById(Integer id);
}
