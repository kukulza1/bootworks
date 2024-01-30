package com.khit.recruit.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khit.recruit.dto.ResumeDTO;
import com.khit.recruit.entity.Resume;
import com.khit.recruit.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ResumeService {
	private final ResumeRepository resumeRepository;
	public void save(ResumeDTO resumeDTO, MultipartFile r_picture) throws IllegalStateException, IOException {
		if(!r_picture.isEmpty()) {
			
			String filePath = "C:\\bootworks\\final_2team\\src\\main\\resources\\static\\upload\\";
			UUID uuid = UUID.randomUUID();
			String filename = uuid + "_" + r_picture.getOriginalFilename();
		
			File savedfile = new File(filePath,filename);
			r_picture.transferTo(savedfile);			
	
			resumeDTO.setR_picture(filename);
			resumeDTO.setR_picture("/upload/" + filename); 
		}
		Resume resume = Resume.tosaveEntity(resumeDTO);
		resumeRepository.save(resume);
		
	}

}
