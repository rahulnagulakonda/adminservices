package com.admin.main.model;

import org.springframework.stereotype.Component;

@Component
public class Login {
	
	private User user;
	private String token;
	
	public Login() {
		super();
	}

	public Login(User user, String token) {
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
