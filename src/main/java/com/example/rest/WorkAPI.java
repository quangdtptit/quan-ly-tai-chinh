package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.WorkDTO;
import com.example.service.WorkService;

@RestController
@RequestMapping("/api/v1/works")
public class WorkAPI {

	@Autowired
	private WorkService workService;

	@GetMapping("/{idSalary}")
	public List<WorkDTO> getAllBySalary(@PathVariable Integer idSalary) {
		return workService.getAllByIdSalary(idSalary);
	}
}
