package com.clodi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/")
	public RedirectView redirectOnStartup(RedirectAttributes attributes) {
		return new RedirectView("home");
	}

}
