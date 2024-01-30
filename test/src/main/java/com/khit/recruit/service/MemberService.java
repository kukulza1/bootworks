package com.khit.recruit.service;

import org.springframework.stereotype.Service;

import com.khit.recruit.entity.Member;
import com.khit.recruit.repository.MemberRepository;
import com.khit.recruit.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository memberRepository;
	
	public Member findById(Long id) {
		return memberRepository.findById(id).get();
		
	}

}
