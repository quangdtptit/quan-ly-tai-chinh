package com.example.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.BillWareHouseEntity;
import com.example.model.ItemEntity;
import com.example.model.WareHouseEntity;
import com.example.model.dto.ItemDTO;

@Component
public class ItemConverter {
	
	@Autowired
	private ModelMapper mapper;

	public ItemDTO toDTO(ItemEntity entity) {
		return mapper.map(entity, ItemDTO.class);
	}

	public ItemEntity toEntity(ItemDTO dto) {
		return mapper.map(dto, ItemEntity.class);
	}

	public List<ItemDTO> toDTOs(List<ItemEntity> entities) {
		List<ItemDTO> dtos = new ArrayList<>();
		entities.forEach(e -> {
			dtos.add(this.toDTO(e));
		});
		return dtos;
	}

	public List<ItemEntity> toEntities(List<ItemDTO> dtos, BillWareHouseEntity billWareHouseEntity, WareHouseEntity wareHouseEntity) {
		List<ItemEntity> entities = new ArrayList<>();
		dtos.forEach(e -> {
			ItemEntity itemEntity = this.toEntity(e);
			itemEntity.setBillWareHouseEntity(billWareHouseEntity);
			itemEntity.setWareHouseEntity(wareHouseEntity);
			entities.add(itemEntity);
		});
		return entities;
	}
}
