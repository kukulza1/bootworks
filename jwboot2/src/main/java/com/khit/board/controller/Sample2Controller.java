package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sample2Controller {
	
	@GetMapping("/sample2")
	//@ResponseBody //데이터전달역할 어노테이션
	public @ResponseBody String test() {
		return"감삼다";
	}

}
