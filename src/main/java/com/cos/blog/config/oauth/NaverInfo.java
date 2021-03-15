package com.cos.blog.config.oauth;

import java.util.Map;

public class NaverInfo extends OAuth2UserInfo {

	public NaverInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return (String)attributes.get("id");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String)attributes.get("name");
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String)attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		return "";
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Naver_" + attributes.get("id").toString();
	}
}
