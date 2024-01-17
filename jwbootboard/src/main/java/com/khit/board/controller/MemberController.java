package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/member/join")
	public String joinform() {
		return"/member/join"; // join.html
	}
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberdto) {
		System.out.println("email="+memberdto);
		memberService.save(memberdto);
		return"redirect:/";//login.html
	}
	@GetMapping("/member/list")
	public String getList(Model model) {
		List<MemberDTO> memberList = memberService.findAll();
		model.addAttribute("memberlist", memberList);
		return"/member/list";
	}
	//회원상세
	//@PathVariable-경로에 변수를 할당
	@GetMapping("/member/{id}")
	public String getMember(@PathVariable Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "/member/detail";
	}
	@GetMapping("/member/delete/{id}")
	public String deleteMember(@PathVariable Long id) {
		memberService.delete(id);
		return"redirect:/member/list" ;
	}
	@GetMapping("/member/login")
	public String loginform() {
		
		return"/member/login";
	}
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session,
			      Model model) {
		MemberDTO loginmember = memberService.login(memberDTO);
		if(loginmember != null) {
			session.setAttribute("sessionEmail", loginmember.getMemberEmail());
			session.setAttribute("sessionName", loginmember.getMemberName());
			return"main";
		}else {
			String error ="아이디나 비번을 확인해주세요";
			model.addAttribute("error", error);
			return"/member/login";
		}
		
		
		
	}
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return"redirect:/";
	}
	@GetMapping("/member/update")
	public String updateform(HttpSession session,Model model) {
		String email = (String) session.getAttribute("sessionEmail");
		MemberDTO memberDTO = memberService.findByEmail(email);
		model.addAttribute("member", memberDTO);
		return"/member/update";
	}
	@PostMapping("/member/update")
	public String updateMember(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/"+memberDTO.getId();
	}
	@PostMapping("/member/check-email")
	public @ResponseBody String checkEmail(@RequestParam("memberEmail")String memberEmail) {
		String resultText = memberService.ckeckEmail(memberEmail);
		if(resultText.equals("OK")) {
			return resultText;
		}else {
			return null;
		}
	
	}

}







