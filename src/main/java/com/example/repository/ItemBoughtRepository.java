package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ItemBoughtEntity;

@Repository
public interface ItemBoughtRepository extends JpaRepository<ItemBoughtEntity, Integer> {

}
