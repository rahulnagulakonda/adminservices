package com.admin.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.main.model.User;
import com.admin.main.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	//login
	@PostMapping()
	public ResponseEntity userLogin(@RequestBody User user) {
		try {
			return new ResponseEntity(loginService.userLogin(user),HttpStatus.ACCEPTED);
		} catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.UNAUTHORIZED);
		}
	}

}