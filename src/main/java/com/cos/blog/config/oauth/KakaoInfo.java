package com.cos.blog.config.oauth;

import java.util.Map;

public class KakaoInfo extends OAuth2UserInfo {

	public KakaoInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return attributes.get("id").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		Map<String, Object> temp = (Map) attributes.get("properties");
		return (String) temp.get("nickname");
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		Map<String, Object> temp = (Map) attributes.get("kakao_account");
		return (String) temp.get("email");
	}

	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		Map<String, Object> temp = (Map) attributes.get("properties");
		return (String) temp.get("profile_image");
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "Kakao_" + attributes.get("id").toString();
	}

}
