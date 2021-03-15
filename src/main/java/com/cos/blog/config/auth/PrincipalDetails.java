package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails,OAuth2User{//userDetails 타입으로 만들어줌
	
	private User user;
	private Map<String, Object> attributes; //OAuth 제공자로 부터 받은 회원 정보
	private boolean oAuth;
	
	public PrincipalDetails(User user,Map<String, Object> attributes) {
		this.user=user;
		this.attributes=attributes;
		this.oAuth=true;
	}
	
	public PrincipalDetails(User user) {
		this.user=user;
	}
	
	//Oauth
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		
		return "아 몰랑";
	}
	
	//UserDetails
	@Override
	public String getPassword() { //패스워드
		// TODO Auto-generated method stub
		return user.getPassword(); //user가 들고있는 패스워드
	}

	@Override
	public String getUsername() { //유저네임
		// TODO Auto-generated method stub
		return user.getUsername(); //user가 들고있는 username
	}

	@Override
	public boolean isAccountNonExpired() { //계정 만료
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //로그인 실패시 Lock 
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //비밀번호 만료
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { //계정활성화
		// TODO Auto-generated method stub
		return true;
	} 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //권한 체크
		// TODO Auto-generated method stub
	      Collection<GrantedAuthority> collectors = new ArrayList<>();
	      collectors.add(()-> "ROLE_"+user.getRole().toString());

		 //String 타입이 아니기 때문에 toString
		
		return collectors;
	}


}
