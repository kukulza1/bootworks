package com.khit.board.service;

import java.util.List;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khit.board.config.SecurityUser;
import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import com.khit.board.excetion.BootBoardException;
import com.khit.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final필요
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder pwencoder;

	public Member login(Member member) {
		// db에서 아이디 조회
		Optional<Member> findMember = 
				memberRepository.findByMemberId(member.getMemberId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}

	public void save(Member member) {
		//1.비밀번호 암호화
		//2.권한설정
		String encPw =pwencoder.encode(member.getPassword());
		member.setPassword(encPw);
		member.setRole(Role.Member);
      memberRepository.save(member);		
	}

	public List<Member> findAll() {
		List<Member> memberlist = memberRepository.findAll();
		return memberlist;
	}

	public Member findById(Integer id) {
		Optional<Member> findmember = memberRepository.findById(id);
		if(findmember.isPresent()) {
			return findmember.get();
		}else {
			throw new BootBoardException("페이지를 찾을수 없습니다.");
		}
	
	}

	public void delete(Integer id) {
		memberRepository.deleteById(id);
		
	}

	public void update(Member member) {
		memberRepository.save(member);
	}

	public Member findByMemberId(String memberId) {
		Optional<Member> mm = memberRepository.findByMemberId(memberId);
		return mm.get();
	}

	public Member findByMemberId(SecurityUser principal) {
		Optional<Member> member = memberRepository.findByMemberId(principal.getUsername());
		return member.get();
	}

	/*public Member findByMemberId(String memberId) {
		
		return memberRepository.findByMemberId(memberId).get();
		
	}*/

	
	

	
}









