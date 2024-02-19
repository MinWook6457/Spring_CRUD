package com.insilicogen.CRUD_PRJ.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insilicogen.CRUD_PRJ.bbs.repository.BoardRepository;
import com.insilicogen.CRUD_PRJ.bbs.service.dto.BoardRequestDto;

@Controller
public class BoardController {

	 @Autowired
	 private BoardRepository boardRepository;
	 
	 @GetMapping("/board/init.do")
	 public String initBoard(Model model) {
		 // 여기에 Board 디비에서 불러와서 테이블에 그릴 데이터 model.attributes로 넘겨주면 될듯?	 
		 return "redirect:/welcome";
	 }
	 
	 @GetMapping("/board/create.do")
	 public String createBoard(Model model) {
		 
		 
		 return "/board/createBoard";
	 }
}
