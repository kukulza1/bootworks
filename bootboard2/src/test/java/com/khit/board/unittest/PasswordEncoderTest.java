package com.khit.board.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import com.khit.board.repository.MemberRepository;

@SpringBootTest
public class PasswordEncoderTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	/*@Test
	public void testInsertData() {
		//일반회원
		Member member = new Member();
		member.setMemberId("khit");
		member.setPassword(pwEncoder.encode("khit123"));
		member.setName("박이레");
		member.setRole(Role.Member);
		
		memberRepository.save(member);
	}*/
	/*
	@Test
	public void testInsertadmin() {
		//관리자
		Member member2 = new Member();
		member2.setMemberId("khit");
		member2.setPassword(pwEncoder.encode("11"));
		member2.setName("관리자");
		member2.setRole(Role.ADMIN);
		
		memberRepository.save(member2);
	}*/

}
