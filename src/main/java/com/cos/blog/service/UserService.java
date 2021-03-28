package com.cos.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.dto.user.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id, UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).get();	//1차 캐시
		String encPassword = bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());
		
		userEntity.setPassword(encPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());
		return userEntity;
	}	// 더티체킹 (데이터가 변했네? 바로 flush) (업데이트는 항상 더티체킹)
}
