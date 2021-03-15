package com.cos.blog.config.oauth;

import java.util.Map;

public class FacebookInfo extends OAuth2UserInfo{

	public FacebookInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return (String) attributes.get("id"); //키값으로 호출
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String) attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Facebook_"+(String) attributes.get("id");
	}
	
}
