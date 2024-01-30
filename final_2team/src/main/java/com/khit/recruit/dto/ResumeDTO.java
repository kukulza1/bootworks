package com.khit.recruit.dto;



import java.time.LocalDateTime;

import com.khit.recruit.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResumeDTO {
	private Integer resumeId;
	
	private String education;
	
	private String career;
	
	private String license;
	
	private String specialNote;
	
	private String content;
	
	private String r_picture;	
	
	private String filepath;
	
	private Member memberId;

}
