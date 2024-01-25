package com.khit.board.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.khit.board.entity.User;
import com.khit.board.exception.CustomException;
import com.khit.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public List<User> findAll() {
		
		return userRepository.findAll(); 
	}

	public User findById(Integer id) {
		//람다식으로 검색해서 없는회원 오류처리
		User finduser = userRepository.findById(id).orElseThrow(()-> {
			return new CustomException(id+"번 회원이 없습니다");
		});
		
	    /*User finduser = userRepository.findById(id).orElseThrow(new Supplier<CustomException>() {

			@Override
			public CustomException get() {
				
				return new CustomException(id+"번회원이 없습니다.");
			}
		});*/
		return finduser;
	}

}

















