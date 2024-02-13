package com.insilicogen.CRUD_PRJ.user.web;

import java.util.List;

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
import com.insilicogen.CRUD_PRJ.user.service.UserRegisterService;
import com.insilicogen.CRUD_PRJ.user.service.DTO.UserDto;
import com.insilicogen.CRUD_PRJ.user.service.DTO.UserFormDto;

@Controller
public class UserController {

	@Autowired
	private UserRegisterService userRegisterService;

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
	public String insertUserJSON(@RequestBody UserFormDto formData) {
		System.out.println("insertUserJSON 컨트롤러 진입");
		UserDto userDto = formData.getBody();

		Integer pswdHintSn = formData.getParam().getPswdHintSn();
		String pswdHintCn = formData.getParam().getHintCn(); // null 값이 가져와짐

		PSWD_HINT pwsdHint = new PSWD_HINT(pswdHintSn, pswdHintCn);

		userRegisterService.registerUser(userDto, pwsdHint);
		return "200";
	}

	@GetMapping("/registerSuccess")
	public String showRegisterSuccessReloadLogin() {
		return "login";
	}

	@PostMapping("/user/checkDuplicateId")
	@ResponseBody
	public String checkDuplicateId(@RequestParam("userLoginId") String userLoginId) {
		System.out.println("중복 검사 컨트롤러 진입");
		boolean isDuplication = userRegisterService.isUserIdDuplicated(userLoginId);

		if (isDuplication)
			return "중복";
		else
			return "사용가능"; // 중복 아님
	}

	@PostMapping("/user/checkPassWord")
	@ResponseBody
	public String checkPassWord(@RequestParam("password") String password) {
		System.out.println("비밀번호 검사 컨트롤러 진입");
		
		if (password.length() < 8 || !password.matches(".*[0-9].*") // 숫자
				|| !password.matches(".*[a-zA-Z].*") // 영문자
				|| !password.matches(".*[!@#$%^&*()].*")) { // 특수문자
			return "비밀번호는 숫자, 영문자, 특수문자를 포함하여 최소 8자리 이상이어야 합니다.";
		} else {
			return "비밀번호가 유효합니다.";
		}
	}
}
