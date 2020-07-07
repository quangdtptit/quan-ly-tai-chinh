package com.example.service;

import java.io.File;
import java.util.List;

import com.example.model.dto.SalaryDTO;

public interface SalaryService {
	List<SalaryDTO> findAllSalaryByMonth(Integer month);

	boolean uploadFile(File file, Integer month, Integer year);

	List<SalaryDTO> findAllByType(String type);

	List<SalaryDTO> findAllDetails(Integer number, String type);
}
