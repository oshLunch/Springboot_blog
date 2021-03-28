package com.cos.blog.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cos.blog.config.oauth.Oauth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration // 설정, 메모리에 띄움 ioc에 등록
@EnableWebSecurity // 이제 커스터마이징한 시큐리티가 실행된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// 어댑터는 함수를 걸러줌, 강제성을 없애준다.
	private final Oauth2DetailsService oAuth2DetailsService;

	// IOC 등록만 하면 AuthenticationManager 가 Bcrypt로 자동 검증해준다.
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
//		 .antMatchers("/user", "/post").authenticated() // /user, /post 는 인증만 검사
				.antMatchers("/user/**", "/post/**", "/reply/**").access("hasRole('ROLE_USER')or hasRole('RoLE_ADMIN')")
				// ROLE_는 강제성이 있음
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // ADMIN권한을 가진자만.
				.anyRequest().permitAll() // 나머지는 전부 허용해준다.
				.and() // 여기서 끝
				.formLogin() // x-www-form-urlencoded , json으로 던지면 안된다. 결국 폼태그를 만들어야한다.
				.loginPage("/loginForm").loginProcessingUrl("/login") // user, post 호출 시 로그인 페이지로 리다이렉션됨
				// login주소로 들어오면 Sping security가 낚아챈다.
//         .successHandler(new AuthenticationSuccessHandler() {
//			
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws IOException, ServletException {
//				// TODO Auto-generated method stub
//				response.sendRedirect("/");
//				
//			}
//		}); 로그인이 성공하면 / 주소로 갈것이다.
				.defaultSuccessUrl("/")// 로그인이 성공하면 / 주소로 갈것이다.
				.and().oauth2Login() // oauth
				.userInfoEndpoint().userService(oAuth2DetailsService);

	}
}
