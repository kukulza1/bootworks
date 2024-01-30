package com.khit.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.recruit.entity.Resume;

public interface ResumeRepository  extends JpaRepository<Resume, Integer>{

}
