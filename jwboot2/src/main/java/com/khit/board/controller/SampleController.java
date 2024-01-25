package com.khit.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khit.board.entity.Users;

@RestController //데이터전달역할인 컨트롤러
public class SampleController {
	
	@GetMapping("/sample")
	public String test() {
		return"hi";
	}
	
	//get-select
	//객체를반환하면 - json형태로 전달됨
	@GetMapping("/khit")
	public Users httpGet() {
		//user1명을 생성후 데이터 검색(보기)
		//Users user = new Users();
		//user.setId(1);
		Users user = Users.builder()
				.id(1)
				.username("today")
				.password("1234")
				.email("khit@naver.com")
				.build();
		return user;
	}
	//post - insert
	//전달받은 데이터가 json형태({key:value})일때 @리퀘스트바디 사용
	@PostMapping("/khit")
	public String httpPost(@RequestBody Users user) {
		return "post요청처리"+user.toString();
	}
	//put - update
	@PutMapping("/khit")
	public String httpPut(@RequestBody Users user) {
		return "put요청처리"+user.toString();
	}
	//delete - delete
	//@Pathvariable은 경로 변수를 전달받음
	@DeleteMapping("/khit/{id}")
	public String httpdelete(@PathVariable Integer id) {
		return "delete요청처리" + id;
	}
	
}
















