package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor //생성자 주입방식 - final을 꼭 붙힘
@Service
public class MemberService {
	private final MemberRepository mr;

	public void save(MemberDTO memberdto) {
		//db안으로 저장(엔티티를 저장해야함)
		//멤버DTO를 entity로 변환해야함 메서드필요
	    Member member =	Member.toSaveEntity(memberdto);
             mr.save(member);	
	}
	
	public List<MemberDTO> findAll() {
		//엔티티를db에서 꺼내와서 디티오로 컨트롤에보냄
		   List<Member> memberList = mr.findAll();
		   
		  List<MemberDTO> memberDTOList = new ArrayList<>();
		  
		  for(Member member : memberList) {
			  MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			  memberDTOList.add(memberDTO);
		  }
		return memberDTOList;
	}

	public MemberDTO findById(Long id) {
		//findById().get();
		    Member member =  mr.findById(id).get();
		    MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
		    
		return memberDTO;
	}

	public void delete(Long id) {
		mr.deleteById(id);
	}

	public MemberDTO login(MemberDTO memberDTO) {
		  Optional<Member> memberEmail =
                      mr.findByMemberEmail(memberDTO.getMemberEmail());
		  if(memberEmail.isPresent()) {
			Member member = memberEmail.get();
			if(member.getMemberPasswd().equals(memberDTO.getMemberPasswd())) {
				MemberDTO DTO = MemberDTO.toSaveDTO(member);
				return DTO;
			}else {
				return null;
			}
		  }else {
			  return null;
		  }
		
	}

	public MemberDTO findByEmail(String email) {
		Member member = mr.findByMemberEmail(email).get();
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
		return memberDTO;
	}

	public void update(MemberDTO memberDTO) {
		Member member = Member.toSaveEntity2(memberDTO);
            mr.save(member);		
	}

}
















