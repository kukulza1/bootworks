package com.khit.recruit.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.khit.recruit.dto.ResumeDTO;
import com.khit.recruit.entity.Member;
import com.khit.recruit.entity.Resume;
import com.khit.recruit.service.MemberService;
import com.khit.recruit.service.ResumeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/resume")
@Controller
public class ResumeController {
	
	private final ResumeService resumeService;
	private final MemberService memberService;
	
	
	@GetMapping("/resume")
	public String resumeForm(
			) {
		//Member member = memberService.findById(id);
		//model.addAttribute("member", member);
		return"/resume/resumeform";
	}
	@PostMapping("/resume")
	public String resume(@ModelAttribute ResumeDTO resumeDTO, 
			MultipartFile r_picture) throws  IOException {
		resumeService.save(resumeDTO,r_picture);
		return"index";
		
	}

}
