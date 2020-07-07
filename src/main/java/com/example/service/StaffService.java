package com.example.service;

import java.util.List;

import com.example.model.dto.StaffDTO;

public interface StaffService {

	StaffDTO getById(Integer id);
	
	List<StaffDTO> getAll();
	
	StaffDTO save(StaffDTO staffDTO);
}
