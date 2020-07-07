package com.example.controller.salary;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.dto.SalaryDTO;
import com.example.service.SalaryService;
import com.example.utils.ExcelUtil;
import com.example.utils.FileUtil;

@Controller
public class SalaryController {

	@Autowired
	private SalaryService salaryService;

	@RequestMapping(value = "/salaries", method = RequestMethod.GET)
	public ModelAndView salaryPageGet(@RequestParam(defaultValue = "1") Integer month) {
		ModelAndView mav = new ModelAndView("salary/list-salary");
		mav.addObject("salaries", salaryService.findAllSalaryByMonth(month));
		mav.addObject("months", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
		mav.addObject("month", month);
		return mav;
	}

	@RequestMapping(value = "/salaries/upload", method = RequestMethod.POST)
	public String uploadFileSalary(@RequestParam MultipartFile file, @RequestParam Integer month,
			@RequestParam Integer year) {
		try {
			File fileExcel = FileUtil.toFile(file);
			boolean check = salaryService.uploadFile(fileExcel, month, year);
			if (!check) {
				return "redirect:/salaries?error";
			}
		} catch (Exception e) {
			return "redirect:/salaries?error";
		}
		return "redirect:/salaries";
	}

	@RequestMapping(value = "/salaries/download", method = RequestMethod.GET)
	public void downloadFileSalary(@RequestParam(defaultValue = "1") Integer month, HttpServletResponse resp) {
		ExcelUtil excelUtil = new ExcelUtil();
		List<SalaryDTO> salaryDTOs = salaryService.findAllSalaryByMonth(month);
		boolean check = excelUtil.exportExcel(salaryDTOs, resp, "bang_luong_thang" + month);
		System.out.println(check);
	}

}
