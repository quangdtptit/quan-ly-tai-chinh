package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.WorkConverter;
import com.example.model.WorkEntity;
import com.example.model.dto.WorkDTO;
import com.example.repository.WorkRepository;
import com.example.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private WorkConverter workConverter;

	@Override
	public List<WorkDTO> getAllByIdSalary(Integer idSalary) {
		List<WorkEntity> entities = workRepository.findByIdSalary(idSalary);
		List<WorkDTO> dtos = new ArrayList<>();
		entities.forEach(e -> {
			dtos.add(workConverter.toDTO(e));
		});
		return dtos;
	}

}
