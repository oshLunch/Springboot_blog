package com.cos.blog.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //메모리에 띄움
public class Oauth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("OAuth 로그인 진행중...");
		System.out.println(userRequest.getAccessToken().getTokenValue());
		
		//1.AcessToken으로 회원정보를 받았다는 의미
		OAuth2User oauth2User = super.loadUser(userRequest);
		//userRequest 는 AcessToken정보,oauth2User 유저정보가 담겨져있다.
		
		System.out.println("============================");
		System.out.println(oauth2User.getAttributes());
		System.out.println("============================");
		
		return processOAuth2User(userRequest,oauth2User);
	}
	
	//구글 로그인 프로세스
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest,OAuth2User oAuth2User) {
		//1.통합 클래스를 생성 = 누군가는 구글, 누군가는 github으로 로그인하면 Attributes정보가 다르기 때문에
		OAuth2UserInfo oAuth2UserInfo = null;
		System.out.println("뭐로 로그인 했을까 : "+userRequest.getClientRegistration().getClientName());
		
		if(userRequest.getClientRegistration().getClientName().equals("Google")) {
			oAuth2UserInfo = new GoogleInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getClientName().equals("Facebook")){
			//Facebook 로그인 시
			oAuth2UserInfo = new FacebookInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getClientName().equals("Naver")){
			//Naver 로그인 시
			oAuth2UserInfo = new NaverInfo((Map)(oAuth2User.getAttributes().get("response")));
		}else if(userRequest.getClientRegistration().getClientName().equals("Kakao")){
			//Kakao 로그인 시
			oAuth2UserInfo = new KakaoInfo((Map)(oAuth2User.getAttributes()));
		}
		
		//2.최초 로그인 = 회원가입+로그인, 최초 로그인x = 로그인
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());
		
		UUID uuid = UUID.randomUUID();
		String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());
		
		if(userEntity ==null) {
			System.out.println("최초 사용자입니다. 자동 회원가입 후 자동 로그인 합니다");
			User user = User.builder()
					.username(oAuth2UserInfo.getUsername())
					.password(encPassword)
					.email(oAuth2UserInfo.getEmail())
					.role(RoleType.USER)
					.build();
			userEntity = userRepository.save(user);
			return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
			
		}else { //이미 회원가입이 완료 됨.(구글 정보가 변경될 수 있기 때문에 update 해야하지만 지금 x)
			//업데이트 로직이 들어가야한다.
			return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
		}

	}

}
