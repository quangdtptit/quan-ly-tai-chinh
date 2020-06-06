package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.SupplierDTO;
import com.example.service.SupplierService;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierAPI {

	@Autowired
	private SupplierService supplierService;

	@GetMapping("/")
	public List<SupplierDTO> getAll() {
		return supplierService.findAll();
	}
}
