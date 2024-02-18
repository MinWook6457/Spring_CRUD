package com.insilicogen.CRUD_PRJ.user.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insilicogen.CRUD_PRJ.user.repository.HintRepository;
import com.insilicogen.CRUD_PRJ.user.service.PSWD_HINT;
import com.insilicogen.CRUD_PRJ.user.service.User;
import com.insilicogen.CRUD_PRJ.user.service.UserService;
import com.insilicogen.CRUD_PRJ.user.service.VO.UserFindPWVO;
import com.insilicogen.CRUD_PRJ.user.service.dto.UserDto;
import com.insilicogen.CRUD_PRJ.user.service.dto.UserFormDto;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private HintRepository hintRepository;

	/**
	 * 회원 가입 화면
	 * 
	 * @return /user/insertUser.jsp
	 */

	@GetMapping("/user/insertUser.do")
	public String insertUser(Model model) {
		System.out.println("insertUser 컨트롤러 진입");
		List<PSWD_HINT> hintQuestions = hintRepository.findAll();
		model.addAttribute("hintQuestions", hintQuestions);
		return "/user/insertUser";
	}

	/**
	 * 회원 등록 폼
	 * 
	 * @return
	 */
	@PostMapping("/user/insertUser.json")
	@ResponseBody
	public User insertUserJSON(@RequestBody @Valid UserFormDto formData) {
		System.out.println("insertUserJSON 컨트롤러 진입");
		UserDto userDto = formData.getBody();

		Integer pswdHintSn = formData.getParam().getPswdHintSn();
		String pswdHintCn = formData.getParam().getHintCn(); 
		
		PSWD_HINT pwsdHint = new PSWD_HINT(pswdHintSn, pswdHintCn);

		User registUser = userService.registerUser(userDto, pwsdHint);
		return registUser;
	}

	@GetMapping("/registerSuccess")
	public String showRegisterSuccessReloadLogin() {
		return "login";
	}

	@PostMapping("/user/checkDuplicateId")
	@ResponseBody
	public boolean checkDuplicateId(@RequestParam("userLoginId") String userLoginId) {
		System.out.println("중복 검사 컨트롤러 진입");
		boolean isDuplication = userService.isUserIdDuplicated(userLoginId);

		if (isDuplication)
			return false; // 중복
		else
			return true; // 유효한 아이디
	}

	@PostMapping("/user/checkPassWord")
	@ResponseBody
	public boolean checkPassWord(@RequestParam("password") String password) {
		System.out.println("비밀번호 검사 컨트롤러 진입");

		Pattern pass = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
		Matcher pm = pass.matcher(password);

		if (!pm.find())
			return false;
		else
			return true;
		/*
		 * if (password.length() < 8 || !password.matches(".*[0-9].*") // 숫자 ||
		 * !password.matches(".*[a-zA-Z].*") // 영문자 ||
		 * !password.matches(".*[!@#$%^&*()].*")) { // 특수문자 return false; } else {
		 * return true; }
		 */
	}

	/* 유저 비밀 번호 찾기 페이지로 */
	@GetMapping("/user/findUserInfo")
	public String findUserInfo(Model model) {
		System.out.println("비밀번호 찾기 페이지 컨트롤러 진입");
		List<PSWD_HINT> hintQuestions = hintRepository.findAll();
		model.addAttribute("hintQuestions", hintQuestions);
		return "/user/findUserInfo";

	}

	@PostMapping("/user/findUserPW")
	@ResponseBody
	public String findUserPW(Model model, @RequestBody UserFindPWVO userFindPwVo) {
		String userLoginId = userFindPwVo.getUserLoginId();
		Integer userHintSn = userFindPwVo.getPswdHintSn();
		String hintAnswer = userFindPwVo.getHintAnswer();

		UserDto foundUser = userService.findUserByHint(userLoginId, userHintSn, hintAnswer);

		if (foundUser != null) {
			return foundUser.getPassword(); // 비밀번호를 반환
		} else {
			return "User not found or hint answer is incorrect"; // 유저를 찾지 못하거나 힌트 답변이 일치하지 않는 경우 메시지 반환
		}
	}
	
	@GetMapping("/user/modifyUserInfo.do")
	public String modifyUserInfo(Model model) {

		return "/user/modifyUserInfo";
	}
	
	
}