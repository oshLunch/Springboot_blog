package com.cos.blog.config.oauth;

import java.util.Map;

public abstract class OAuth2UserInfo { //추상클래스
	protected Map<String, Object> attributes; //protected 자신만 사용 

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public Map<String, Object> getAttributes(){
		return attributes;
	}
	//내가 필요한 내용만 작성
	public abstract String getUsername();
	public abstract String getId();
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getImageUrl();
}
