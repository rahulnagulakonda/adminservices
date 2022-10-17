package com.interon.admin.model;

import org.springframework.stereotype.Component;

@Component
public class LoginResponse {
	
	private User user;
	private String token;
	
	public LoginResponse() {
		super();
	}

	public LoginResponse(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
