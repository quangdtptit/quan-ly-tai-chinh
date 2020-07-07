package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.SalaryEntity;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE tbl_bangluong SET status = 'Đã thanh toán' WHERE maBangLuong IN ?1", nativeQuery = true)
	void updateStatus(Integer[] ids);
}
