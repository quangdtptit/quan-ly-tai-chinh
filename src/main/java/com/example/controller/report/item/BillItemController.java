package com.example.controller.report.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.BillItemEntity;
import com.example.repository.BillItemRepository;

@Controller
public class BillItemController {
	
	@Autowired
	private BillItemRepository billItemRepository;

	@RequestMapping("/report/bill-item")
	public ModelAndView initPage() {
		ModelAndView mav = new ModelAndView("report/item/report-bill-item");
		List<BillItemEntity> list = billItemRepository.findAll();
		mav.addObject("list", list);
		return mav;
	}
}
