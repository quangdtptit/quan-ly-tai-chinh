package com.example.service;

import java.util.List;

import com.example.model.dto.WorkDTO;

public interface WorkService {
	List<WorkDTO> getAllByIdSalary(Integer idSalary);
}
