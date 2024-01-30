package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.config.SecurityUser;
import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
    //로그인 페이지 요청 :  /member/login
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";  //login.html
	}
	
	//로그인 처리
	/*@PostMapping("/member/login")
	public String login(@ModelAttribute Member member, HttpSession session) {
		Member loginMember = memberService.login(member);
		if(loginMember != null 
				&& loginMember.getPassword().equals(member.getPassword()) ) {
			//아이디 비밀번호 일치해서 로그인 되면 세션 발급
			session.setAttribute("sessMemberId", loginMember.getMemberId());
			return "main"; 
		}else {
			return "/member/login";
		}
	}*/
	
	//메인 페이지
	@GetMapping("/main")
	public String main() {
		return "main";  //main.html
	}
	
	@GetMapping("/member/logout")
	public String logout() {		
		return "redirect:/";		
	}
	
	@GetMapping("/member/join")
	public String joinform() {
		return"/member/join";
	}
	
	@PostMapping("/member/join")
	public String join(@ModelAttribute Member member) {
		memberService.save(member);	
		return"redirect:/";
	}
	
	@GetMapping("/member/list")
	public String list(Model model) {
		List<Member> memberlist = memberService.findAll();
		model.addAttribute("memberlist", memberlist);
		return"/member/list";
	}
	
	@GetMapping("/member/{id}")
	public String memberdetail(@PathVariable Integer id, Model model) {
		Member member = memberService.findById(id);
		model.addAttribute("member", member);
		
		return"/member/detail";
	}
	
	@GetMapping("/member/delete/{id}")
	public String deletemember(@PathVariable Integer id) {
		memberService.delete(id);
		return"redirect:/member/list";	
	}
	@GetMapping("/member/update/{id}")
	public String updateform(@PathVariable Integer id , Model model) {
		Member member = memberService.findById(id);
		model.addAttribute("member", member);
		return "/member/update";
		
	}
	@GetMapping("/member/update")
	public String updateform(@AuthenticationPrincipal SecurityUser principal, Model model) {
		Member member = memberService.findByMemberId(principal);
		model.addAttribute("member", member);
		return "/member/update";
		
	}
	/*@GetMapping("/member/update")
	public String updateform2( Model model) {
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		String memberId = au.getName();
		Member member = memberService.findByMemberId(memberId);
		model.addAttribute("member", member);
		return "/member/update";
		
	}*/
	@PostMapping("/member/update")
	public String update(@ModelAttribute Member member) {
		memberService.update(member);
		
		return"redirect:/member/list";
	}
	
	
	
}

























