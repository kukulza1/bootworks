package com.khit.board.dto;

import com.khit.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDTO {
	private Long id;
	private String memberEmail;
	private String memberPasswd;
	private String memberName;
	private int memberAge;
	
	public static MemberDTO toSaveDTO(Member member) {
		/*MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(member.getId());
		memberDTO.setMemberPasswd(member.getMemberPasswd());
		memberDTO.setMemberEmail(member.getMemberEmail());
		memberDTO.setMemberName(member.getMemberName());
		memberDTO.setMemberAge(member.getMemberAge());*/
		MemberDTO memberDTO = MemberDTO.builder()
				             .id(member.getId())
				             .memberEmail(member.getMemberEmail())
				             .memberPasswd(member.getMemberPasswd())
				             .memberName(member.getMemberName())
				             .memberAge(member.getMemberAge())
				             .build();
		return memberDTO;
		
	}

}




