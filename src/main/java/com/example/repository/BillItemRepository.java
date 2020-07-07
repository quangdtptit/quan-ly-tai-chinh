package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.BillItemEntity;

@Repository
public interface BillItemRepository extends JpaRepository<BillItemEntity, Integer> {

}
