package com.insilicogen.CRUD_PRJ.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebLoginController {
	@GetMapping("index")
	public String test(Model model) {
		return "index";
	}
	
	@GetMapping("login")
	public String login(Model model) {
		return "login";
	}
}
