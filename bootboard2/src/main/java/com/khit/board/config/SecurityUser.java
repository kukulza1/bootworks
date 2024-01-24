package com.khit.board.config;

import org.springframework.security.core.authority.AuthorityUtils;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.khit.board.entity.Member;

public class SecurityUser extends User{

	private static final long serialVersionUID = 1L;
	private Member member;
		//3가지 파라메다 - username,password,role			
	public SecurityUser(Member member) {
		//암호화가 안된상태는 {noop}표시
		super(member.getMemberId(), member.getPassword(),  
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
	
  

}
