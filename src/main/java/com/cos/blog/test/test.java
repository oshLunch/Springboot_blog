package com.cos.blog.test;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class test {

	public static void main(String[] args) {
		
		final User user = null;
		
		String respons = "username=ssar&password=1234";
		String[] split = respons.split("&");
		
		String username = split[0].split("=")[1];
		String password = split[1].split("=")[1];
		
		user.setUsername(username);
		user.setPassword(password);
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
	}
}
