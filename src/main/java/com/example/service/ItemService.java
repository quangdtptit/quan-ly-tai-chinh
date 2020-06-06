package com.example.service;

import java.util.List;

import com.example.model.BillWareHouseEntity;
import com.example.model.WareHouseEntity;
import com.example.model.dto.ItemDTO;

public interface ItemService {

	long saveList(List<ItemDTO> list, BillWareHouseEntity billWareHouseEntity, WareHouseEntity wareHouseEntity);
	
	List<ItemDTO> findAll();
}
