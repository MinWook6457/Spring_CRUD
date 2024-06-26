package com.insilicogen.CRUD_PRJ.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.insilicogen.CRUD_PRJ.bbs.service.BoardService;
import com.insilicogen.CRUD_PRJ.user.service.UserService;
import com.insilicogen.CRUD_PRJ.user.service.VO.UserLoginVo;
import com.insilicogen.CRUD_PRJ.user.service.dto.UserDto;

@Controller
public class WebLoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardService boardService;

	@GetMapping("hello")
	public String test(Model model) {
		return "hello";
	}
	/*
	@GetMapping("/home")
	public ModelAndView home(ModelAndView model) { // 홈 페이지
		model.setViewName("home");
		
		return model;
	//	return "home";
	}
	*/
	
	@GetMapping("/home")
	public String home() { // 홈 페이지
		return "home";
	}
	
	@GetMapping("/welcome")
	public String welcome(Model model) {
//	    List<Board> boardList = boardService.getAllBoards();
		
//	    if (boardList.isEmpty()) {
//	        String message = "게시글이 없습니다.";
//	        model.addAttribute("message", message);
//	        return "/welcome";
//	    } else {
//	        model.addAttribute("boardList", boardList);
//	        return "/welcome";
//	    }
	    
	    return "/welcome";
	}

	@PostMapping("/home/login")
	@ResponseBody
	public String login(@RequestBody UserLoginVo userLoginVo,						
						HttpServletRequest req, RedirectAttributes rdat) {
		String userLoginId = userLoginVo.getUserLoginId();
		String password = userLoginVo.getUserPassWord();

		// 로그인이 실패한 경우
		if (userService.authenticate(userLoginId, password) == null) {
			// 실패 메시지를 플래시 속성에 추가하고 홈 페이지로 리다이렉트
			rdat.addFlashAttribute("loginFail", "아이디 혹은 비밀번호가 잘못 되었습니다.");
			return "redirect:/home";
		}else {
			UserDto loginUserDto = userService.authenticate(userLoginId, password);
			// 세션을 획득하고 로그인된 사용자 정보를 세션에 저장
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", loginUserDto);

			// 환영 페이지로 리다이렉트
			return "redirect:/welcome";
		}
	}

	@PostMapping("/home/guestLogin")
	@ResponseBody
	public String guestLogin(HttpServletRequest req, RedirectAttributes rdat){
		System.out.println("비회원 로그인 컨트롤러 진입");

		UserDto guestLoginUser = new UserDto();
		guestLoginUser.setUserLoginId("guest");
		guestLoginUser.setUserNm("비회원");

		HttpSession session = req.getSession();
		session.setAttribute("loginUser", guestLoginUser);

		return "redirect:/home";
	}
}