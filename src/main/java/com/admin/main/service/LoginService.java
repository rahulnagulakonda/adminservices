package com.admin.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.main.model.Login;
import com.admin.main.model.User;
import com.admin.main.repository.RoleRepository;
import com.admin.main.repository.UserRepository;
import com.admin.main.repository.UserRoleMappingRepository;
import com.admin.main.util.JwtGenerator;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRoleMappingRepository urmRepo;
	
	@Autowired
	private JwtGenerator jwt;
	
	//login 
	public Login userLogin(User user) throws RuntimeException{
		List<User> allUsers = userRepo.findAll();
		
		User authUser = userRepo.findByEmail(user.getEmail());
		if(authUser==null) {
			throw new RuntimeException("Incorrect Email or User Doesn't Exist");
		} else {
			if(authUser.getStatus().equals("deactivated")) {
				throw new RuntimeException("Please contact admin to active your account");
			} else {
				if(!authUser.getPassword().equals(user.getPassword())) {
					throw new RuntimeException("Incorrect Password");
				} else {
					String token = jwt.generateJwt(authUser);
					return new Login(authUser,token);
				}
			}
		}
	}
}
