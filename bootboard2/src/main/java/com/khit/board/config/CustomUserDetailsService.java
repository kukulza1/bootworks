package com.khit.board.config;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khit.board.entity.Member;
import com.khit.board.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//db에 있는 회원정보를 조회
		//UserDetails 타입의 객체를 반환함
		Optional<Member> findmember = memberRepository.findByMemberId(username);
		if(findmember.isEmpty()) {
			throw new UsernameNotFoundException(username+"사용자없음");
		}else {
			Member member = findmember.get();
			return new SecurityUser(member);
		}
		
		
	}

}
















