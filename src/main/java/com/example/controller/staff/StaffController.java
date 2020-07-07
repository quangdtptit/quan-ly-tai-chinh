package com.example.controller.staff;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.dto.StaffDTO;
import com.example.repository.BranchRepository;
import com.example.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	private StaffService staffService;

	@Autowired
	private BranchRepository branchRepository;

	@RequestMapping(value = "/staffs", method = RequestMethod.GET)
	public ModelAndView listStaffPage() {
		ModelAndView mav = new ModelAndView("staff/list-staff");
		mav.addObject("staffs", staffService.getAll());
		return mav;
	}

	@RequestMapping(value = { "/staffs/", "/staffs/{id}" }, method = RequestMethod.GET)
	public ModelAndView editsStaffPage(@PathVariable(required = false) Integer id) {
		List<String> roles = Arrays.asList("ADMIN", "WAREHOUSE", "USER","ACCOUNT");
		StaffDTO staffDTO = new StaffDTO();
		ModelAndView mav = new ModelAndView("staff/edits-staff");
		if (id != null) {
			staffDTO = staffService.getById(id);
		}
		mav.addObject("branchs", branchRepository.findAll());
		mav.addObject("staff", staffDTO);
		mav.addObject("roles", roles);
		return mav;
	}

	@RequestMapping(value = { "/staffs/" }, method = RequestMethod.POST)
	public String editsStaffPagePost(@ModelAttribute StaffDTO staffDTO) {
		staffService.save(staffDTO);
		return "redirect:/staffs";
	}
}
