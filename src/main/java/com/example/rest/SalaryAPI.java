package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.SalaryDTO;
import com.example.repository.SalaryRepository;
import com.example.service.SalaryService;

@RestController
@RequestMapping("/api/v1/salaries")
public class SalaryAPI {

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private SalaryService salaryService;

	@PutMapping
	public Integer doPayment(@RequestBody Integer[] ids) {
		try {
			salaryRepository.updateStatus(ids);
			return HttpStatus.OK.value();
		} catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
	}

	@GetMapping("/{number}")
	public List<SalaryDTO> reportSalaryDetails(@PathVariable Integer number, @RequestParam String type) {
		return salaryService.findAllDetails(number, type);
	}
	
	@GetMapping("")
	public List<SalaryDTO> reportSalary(@RequestParam String type){
		List<SalaryDTO> salaryDTOs = salaryService.findAllByType(type);
		List<SalaryDTO> salaries = new ArrayList<>();
		try {
			SalaryDTO salaryDTO = new SalaryDTO();
			if (type.equals("month")) {
				salaryDTO.setType("Tháng " + 1);
			} else {
				salaryDTO.setType("Quý " + 1);
			}
			for (int i = 0; i < salaryDTOs.size(); i++) {
				if (salaryDTOs.get(i).getType().equals(salaryDTO.getType())) {
					salaryDTO.setNumberWork(salaryDTOs.get(i).getNumberWork() + salaryDTO.getNumberWork());
					salaryDTO.setSum(salaryDTO.getSum() + salaryDTOs.get(i).getSum());
					salaryDTO.setPunish(salaryDTO.getPunish() + salaryDTOs.get(i).getPunish());
					salaryDTO.setReward(salaryDTO.getReward() + salaryDTOs.get(i).getReward());
				} else {
					salaries.add(salaryDTO);
					salaryDTO = new SalaryDTO();
					salaryDTO.setType(salaryDTOs.get(i).getType());
					salaryDTO.setNumberWork(salaryDTOs.get(i).getNumberWork() + salaryDTO.getNumberWork());
					salaryDTO.setSum(salaryDTO.getSum() + salaryDTOs.get(i).getSum());
					salaryDTO.setPunish(salaryDTO.getPunish() + salaryDTOs.get(i).getPunish());
					salaryDTO.setReward(salaryDTO.getReward() + salaryDTOs.get(i).getReward());
				}
			}
			salaries.add(salaryDTO);
		} catch (Exception e) {
		}
		return salaries;
	}


}
