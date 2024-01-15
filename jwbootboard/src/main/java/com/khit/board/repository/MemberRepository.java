package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

//jpa 레포지터리를 상속받아야함 (@Repository 생략가능)
public interface MemberRepository extends JpaRepository<Member, Long>{
	//메서드 제공 -save() findAll(), delete(), findById()
	
	//쿼리메서드(쿼리이름이 메서드임) optional(null 체크 클래스)
	//select * from tbl_member where member_email = ?;
    Optional<Member> findByMemberEmail(String email) ;
		
	

}
