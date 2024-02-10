package com.insilicogen.CRUD_PRJ.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insilicogen.CRUD_PRJ.user.service.UserRegisterService;
import com.insilicogen.CRUD_PRJ.user.service.DTO.UserDto;

@Controller
public class UserController {

	@Autowired
	private UserRegisterService userRegisterService;

	/**
	 * 회원 가입 화면
	 * 
	 * @return /user/insertUser.jsp
	 */
	
	@GetMapping("/user/insertUser.do")
	public String insertUser() {
		return "/user/insertUser";
	}
	

	/**
	 * 회원 등록 폼
	 * 
	 * @return
	 */
	@PostMapping("/user/insertUser.json")
	@ResponseBody
	public String insertUserJSON(@RequestBody UserDto userDto) {
	    userRegisterService.registerUser(userDto); 	
	    return "200"; // 임시로 200 
	}


	@GetMapping("/registerSuccess")
	public String showRegisterSuccessReloadLogin() {
		return "login";
	}
}
