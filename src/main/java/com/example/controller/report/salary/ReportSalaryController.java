package com.example.controller.report.salary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.dto.SalaryDTO;
import com.example.service.SalaryService;

@Controller
public class ReportSalaryController {

	@Autowired
	private SalaryService salaryService;

	@RequestMapping(value = "/report/salaries")
	public ModelAndView reportSalaryPage(@RequestParam(defaultValue = "month") String type) {
		ModelAndView mav = new ModelAndView("report/salary/report-salary");
		List<SalaryDTO> salaryDTOs = salaryService.findAllByType(type);
		List<SalaryDTO> salaries = new ArrayList<>();
		try {
			SalaryDTO salaryDTO = new SalaryDTO();
			if (type.equals("month")) {
				mav.addObject("type", "Tháng");
				salaryDTO.setType("Tháng " + 1);
			} else {
				mav.addObject("type", "Quý");
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
		mav.addObject("salaries", salaries);
		return mav;
	}
}
