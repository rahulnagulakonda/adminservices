package com.interon.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interon.admin.model.LoginForm;
import com.interon.admin.model.LoginResponse;
import com.interon.admin.model.User;
import com.interon.admin.repository.UserRepository;
import com.interon.admin.util.JwtUtils;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtils jwt;
	
	@Autowired
	private LoginResponse loginResponse;
	
	//login 
	public LoginResponse userLogin(LoginForm loginDetails) throws RuntimeException{
		
		String userName = loginDetails.getUserName();
		
		User emailLogin = userRepo.findByEmail(userName);
		User userNameLogin = userRepo.findByUserName(userName);
		
		if(emailLogin==null && userNameLogin==null) {
			throw new RuntimeException("User Doesn't Exist");
		} else {
			if(emailLogin!=null) {
				if(!emailLogin.getStatus().equals("active")) {
					throw new RuntimeException("Please contact admin to active your account");
				} else {
					if(!emailLogin.getPassword().equals(loginDetails.getPassword())) {
						throw new RuntimeException("Incorrect Password");
					} else {
						String token = jwt.generateJwt(emailLogin);
						loginResponse.setUser(emailLogin);
						loginResponse.setToken(token);
						return loginResponse;
					}
				}
			} else {
				if(!userNameLogin.getStatus().equals("active")) {
					throw new RuntimeException("Please contact admin to active your account");
				} else {
					if(!userNameLogin.getPassword().equals(loginDetails.getPassword())) {
						throw new RuntimeException("Incorrect Password");
					} else {
						String token = jwt.generateJwt(userNameLogin);
						loginResponse.setUser(userNameLogin);
						loginResponse.setToken(token);
						return loginResponse;
					}
				}
			}
		}
	}
}
