package com.insilicogen.CRUD_PRJ.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("index")
	public String test(Model model) {
		return "index";
	}
	
}
