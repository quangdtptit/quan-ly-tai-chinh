package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.SupplierEntity;
import com.example.model.dto.SupplierDTO;

@Component
public class SupplierConverter {
	
	@Autowired
	private ModelMapper mapper;
	 
	public SupplierDTO toDTO(SupplierEntity entity) {
		return mapper.map(entity, SupplierDTO.class);
	}
	
}
