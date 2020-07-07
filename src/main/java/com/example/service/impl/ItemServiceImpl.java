package com.example.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.ItemConverter;
import com.example.model.BillWareHouseEntity;
import com.example.model.ItemEntity;
import com.example.model.WareHouseEntity;
import com.example.model.dto.ItemDTO;
import com.example.repository.ItemRepository;
import com.example.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemConverter itemConverter;

	@Override
	public long saveList(List<ItemDTO> list, BillWareHouseEntity billWareHouseEntity, WareHouseEntity wareHouseEntity) {
		List<ItemEntity> entities = itemRepository
				.saveAll(itemConverter.toEntities(list, billWareHouseEntity, wareHouseEntity));
		return entities.size();
	}

	@Override
	public List<ItemDTO> findAll() {
		List<ItemEntity> entities = itemRepository.findAll();
		List<ItemDTO> result = new ArrayList<>();
		entities.forEach(e -> {
			result.add(itemConverter.toDTO(e));
		});
		return result;
	}

	@Override
	public ItemDTO findById(Integer id) {
		Optional<ItemEntity> optItem = itemRepository.findById(id);
		if (optItem.isPresent()) {
			return itemConverter.toDTO(optItem.get());
		}
		return null;
	}

	@Override
	public ItemDTO save(ItemDTO itemDTO) {
		ItemEntity entity = itemConverter.toEntity(itemDTO);
		entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
		entity = itemRepository.save(entity);
		return itemConverter.toDTO(entity);
	}

	@Override
	public boolean delete(Integer id) {
		try {
			itemRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean itemOuput(ItemDTO itemDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
