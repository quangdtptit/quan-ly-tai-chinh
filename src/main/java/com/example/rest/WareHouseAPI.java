package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.WareHouseDTO;
import com.example.service.WareHouseService;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WareHouseAPI {

	@Autowired
	private WareHouseService wareHouseService;

	@GetMapping("/")
	public List<WareHouseDTO> getAll() {
		return wareHouseService.findAll();
	}
}
