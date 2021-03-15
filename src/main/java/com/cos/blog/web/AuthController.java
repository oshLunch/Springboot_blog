package com.cos.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blog.service.AuthService;
import com.cos.blog.web.auth.dto.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {

	private final AuthService authService;
	
	// 주소 : 인증안되었을 때/user,/post, 인증이 되든말던 무조건/loginForm
	@GetMapping("/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "auth/joinForm";
	}

	@PostMapping("/join")
	public String join(AuthJoinReqDto authJoinReqDto) {
		authService.회원가입(authJoinReqDto.toEntity());
		return "redirect:/loginForm";
	}
}
