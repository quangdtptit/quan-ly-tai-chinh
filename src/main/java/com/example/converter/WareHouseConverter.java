package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.WareHouseEntity;
import com.example.model.dto.WareHouseDTO;

@Component
public class WareHouseConverter {
	
	@Autowired
	private ModelMapper mapper;
	
	public WareHouseDTO toDTO(WareHouseEntity entity) {
		return mapper.map(entity, WareHouseDTO.class);
	}
}
