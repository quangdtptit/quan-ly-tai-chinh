package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.StaffEntity;
import com.example.model.dto.StaffDTO;

@Component
public class StaffConverter {

	@Autowired
	private ModelMapper mapper;

	public StaffDTO toDTO(StaffEntity entity) {
		StaffDTO result = mapper.map(entity, StaffDTO.class);
		try {
			result.setIdBranch(entity.getBranchEntity().getId());
			result.setNameBranch(entity.getBranchEntity().getName());
		} catch (Exception e) {
		}
		return result;
	}

	public StaffEntity toEntity(StaffDTO dto) {
		StaffEntity entity = mapper.map(dto, StaffEntity.class);
		return entity;
	}
}
