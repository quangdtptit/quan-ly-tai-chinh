package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.SupplierConverter;
import com.example.model.SupplierEntity;
import com.example.model.dto.SupplierDTO;
import com.example.repository.SupplierRepository;
import com.example.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private SupplierConverter supplierConverter;

	@Override
	public List<SupplierDTO> findAll() {
		List<SupplierDTO> dtos = new ArrayList<>();
		List<SupplierEntity> entities = supplierRepository.findAll();
		entities.forEach(e -> {
			dtos.add(supplierConverter.toDTO(e));
		});
		return dtos;
	}

}
