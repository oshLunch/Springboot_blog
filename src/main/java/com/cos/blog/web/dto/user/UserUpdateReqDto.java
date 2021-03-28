package com.cos.blog.web.dto.user;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	private String username;
	private String password;
	private String email;
	
	// toEntity 만들지 않는 이유는 더티체킹을 할 것이기 때문!
}
