package com.khit.board.dto;

import java.sql.Timestamp;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDTO {
	
	
	private Integer id;
	
	@Size(min=4,max=20)
	@NotEmpty(message="사용자아이디는 필수항목입니다.")
	private String memberId;
	@NotEmpty(message="비밀번호는 필수항목입니다.")
	private String password;
	@NotEmpty(message="사용자이름은 필수항목입니다.")
	private String name;
	
	private Role role;
	
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	//entity(model<db>에 저장됨) -> dto(view로 보기)로 변환
	//목록보기, 상세보기
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.memberId(member.getMemberId())
				.password(member.getPassword())
				.name(member.getName())
				.role(member.getRole())
				.createdDate(member.getCreatedDate())
				.updatedDate(member.getUpdatedDate())
				.build();		
		return memberDTO;
	}
}













