package com.khit.board.entity;

import com.khit.board.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "tbl_member")
@Entity
public class Member {
	@Id //pk
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)//유일성가짐,중복검사
	private String memberEmail;
	
	@Column(nullable = false)//필수입력,낫널
	private String memberPasswd;
	
	@Column(length = 30, nullable = false)//길이 30byte
	private String memberName;
	
	@Column
	private int memberAge;
	
	//dto를 매개변수로 받아서 엔티티에 저장하는 메서드 생성
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = new Member();
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPasswd(memberDTO.getMemberPasswd());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		
		return member;
		
	}
	public static Member toSaveEntity2(MemberDTO memberDTO) {
		Member member = new Member();
		member.setId(memberDTO.getId());
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPasswd(memberDTO.getMemberPasswd());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		
		return member;
		
	}
	
	
	
	

}







