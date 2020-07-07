package com.example.controller.report.stocker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportStockerController {

	@RequestMapping(value = "/report/stocker", method = RequestMethod.GET)
	public ModelAndView initPage() {
		ModelAndView mav = new ModelAndView("report/stocker/report-stocker");
		return mav;
	}

}
