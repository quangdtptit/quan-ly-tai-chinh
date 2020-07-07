package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.SalaryEntity;
import com.example.model.WorkEntity;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {
	List<WorkEntity> findBySalaryEntity(SalaryEntity salaryEntity);
	
	@Query(value = "SELECT BL.* FROM tbl_bangcong AS BL WHERE BL.maBangLuong = ?1", nativeQuery = true)
	List<WorkEntity> findByIdSalary(Integer idSalary);
}
