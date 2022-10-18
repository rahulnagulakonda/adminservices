package com.interon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interon.admin.model.LoginForm;
import com.interon.admin.model.User;
import com.interon.admin.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	//login
	@PostMapping()
	public ResponseEntity<Object> login(@RequestBody LoginForm loginDetails) {
		try {
			return new ResponseEntity<>(loginService.userLogin(loginDetails),HttpStatus.ACCEPTED);
		} catch(RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(),HttpStatus.UNAUTHORIZED);
		}
	}

}
