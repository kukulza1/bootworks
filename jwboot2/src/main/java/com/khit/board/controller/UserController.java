package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.khit.board.entity.User;
import com.khit.board.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;
	
	//포스트면에 json형태의 자료를 입력받아서 DB에 저장
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		userService.save(user);
		return "회원가입 성공";
	}
	@GetMapping("/user/list")
	public @ResponseBody List<User> getList(){
		List<User> userlist = userService.findAll();
		return userlist;
	}
	@GetMapping("/user/{id}")
	public @ResponseBody User getuser(@PathVariable Integer id) {
		User user = userService.findById(id);
		return user;
		
	}
	

}
