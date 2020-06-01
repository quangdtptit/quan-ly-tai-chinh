package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.repository.CustomerRepository;

@Controller
public class HomeController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage() {
		System.out.println(customerRepository.count());
		return "home";
	}

}
