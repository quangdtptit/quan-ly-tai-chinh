package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.WareHouseConverter;
import com.example.model.WareHouseEntity;
import com.example.model.dto.WareHouseDTO;
import com.example.repository.WareHouseRepository;
import com.example.service.WareHouseService;

@Service
public class WareHouseServiceImpl implements WareHouseService {

	@Autowired
	private WareHouseRepository wareHouseRepository;

	@Autowired
	private WareHouseConverter wareHouseConverter;

	@Override
	public List<WareHouseDTO> findAll() {
		List<WareHouseDTO> result = new ArrayList<>();
		List<WareHouseEntity> entities = wareHouseRepository.findAll();
		entities.forEach(e -> {
			result.add(wareHouseConverter.toDTO(e));
		});
		return result;
	}

}
