package com.example.utils;

import java.util.List;

import com.example.model.BillWareHouseEntity;
import com.example.model.dto.ItemDTO;

public class CommonUtil {

	public static BillWareHouseEntity caculation(List<ItemDTO> itemDTOs) {
		int total = 0;
		long amount = 0;
		for (ItemDTO itemDTO : itemDTOs) {
			total += itemDTO.getTotal();
			amount += itemDTO.getPrice();
		}
		BillWareHouseEntity billWareHouseEntity = new BillWareHouseEntity();
		billWareHouseEntity.setTotal(total);
		billWareHouseEntity.setAmount(amount);
		return billWareHouseEntity;
	}
}
