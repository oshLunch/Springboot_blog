package com.cos.blog.config.oauth;

import java.util.Map;

public class GoogleInfo extends OAuth2UserInfo{

	public GoogleInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return (String) attributes.get("sub"); //키값으로 호출
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
		return (String) attributes.get("picture");
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Google_"+(String) attributes.get("sub");
	}
	
}
