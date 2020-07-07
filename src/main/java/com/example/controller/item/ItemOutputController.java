package com.example.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.repository.ItemOuputRepository;

@Controller
public class ItemOutputController {

	@Autowired
	private ItemOuputRepository itemOuputRepository;

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ModelAndView initPage() {
		ModelAndView mav = new ModelAndView("item-output/list-item-output");
		mav.addObject("items", itemOuputRepository.findAll());
		return mav;
	}
	
	@RequestMapping(value = "/items/order", method = RequestMethod.GET)
	public ModelAndView pageOrder() {
		ModelAndView mav = new ModelAndView("item-output/order");
		return mav;
	}
	
}
