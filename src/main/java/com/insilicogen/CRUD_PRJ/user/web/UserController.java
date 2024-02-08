package com.insilicogen.CRUD_PRJ.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.insilicogen.CRUD_PRJ.user.service.UserRegisterService;
import com.insilicogen.CRUD_PRJ.user.service.DTO.UserDto;

@Controller
public class UserController {
	private final UserRegisterService userRegisterService;

	public UserController(UserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}

	@PostMapping("/register") // CREATE
	public String register(@ModelAttribute UserDto userDto, Model model) {
		userRegisterService.registerUser(userDto);
		return "redirect:/registerSuccess";
	}

	@GetMapping("/registerForm") // 회원 가입 폼
	public String showRegisterForm() {
		return "registerForm";
	}

	@GetMapping("/registerSuccess") // 회원 가입 성공 리다이렉트
	public String showRegisterSuccessPage() {
		return "registerSuccess";
	}
}
