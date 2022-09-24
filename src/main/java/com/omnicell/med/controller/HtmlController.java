package com.omnicell.med.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HtmlController {

	@GetMapping()
	public ModelAndView homePage(Model model) {
		return new ModelAndView("ui/main");
	}

	@GetMapping("/restock")
	public ModelAndView restockPage(Model model) {
		return new ModelAndView("ui/restock");
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashboardPage(Model model) {
		return new ModelAndView("ui/dashboard");
	}
}
