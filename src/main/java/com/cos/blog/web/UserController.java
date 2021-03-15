package com.cos.blog.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.UserService;
import com.cos.blog.web.dto.CommonRespDto;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	// 로그인, 로그아웃, 회원가입, 회원정보 변경(AuthController)
	private final UserService userService;
	
	// /user/1 -> 유저 정보 가져오기
	@GetMapping("/user/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		return "user/updateForm";
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody CommonRespDto<?> update(@PathVariable int id, @RequestBody UserUpdateReqDto userUpdateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		User userEntity = userService.회원수정(id, userUpdateReqDto);
		
		// 세션 변경 하기 (세션과 DB를 동기화)
		// UsernamePasswordToken -> Authentication 객체로 만들어서 -> 시큐리티 컨텍스트 홀더에 집어 넣으면 됨.
		principalDetails.setUser(userEntity);
//		Authentication authentication = 
//				new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new CommonRespDto<>(1,null);
	}

	@GetMapping("/user")
	public @ResponseBody String findAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		// @Controller + @ResponseBody = @RestController // 세션 확인 방법(1)
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();  
//		System.out.println(principalDetails.getUser());

		// (2)@AuthenticationPrincipal 어노테이션 걸어주기
		System.out.println(principalDetails.getUsername());
		return "User";
	}

}
