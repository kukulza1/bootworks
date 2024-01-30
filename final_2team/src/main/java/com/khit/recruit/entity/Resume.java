package com.khit.recruit.entity;


import com.khit.recruit.dto.ResumeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Table
@Entity
public class Resume {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer resumeId;
	
	@Column(nullable= false)
	private String education;
	@Column(nullable= false)
	private String career;
	@Column
	private String license;
	@Column
	private String specialNote;
	@Column
	private String content;
	@Column
	private String r_picture;	
	@Column
	private String filepath;
	
	
	
	public static Resume tosaveEntity(ResumeDTO resumeDTO) { //인서트
		Resume resume = Resume.builder()
				.education(resumeDTO.getEducation())
				.career(resumeDTO.getCareer())
				.license(resumeDTO.getLicense())
				.specialNote(resumeDTO.getSpecialNote())
				.content(resumeDTO.getContent())
				.member(resumeDTO.getMemberId())
				.r_picture(resumeDTO.getR_picture())
				.build();							      			      
		return resume;
		
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberId")
	private Member member;
	
	
	
	
	

}
