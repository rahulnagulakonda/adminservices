package com.admin.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.main.model.User;
import com.admin.main.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//to view the user details 
	@GetMapping("/{empId}/profile")
	public ResponseEntity getUserDetails(@PathVariable int empId) {
		try {
			return new ResponseEntity(userService.findUserDetails(empId),HttpStatus.OK);
		} catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	//to change password for user login
	@PostMapping("/{empId}/changepassword")
	public ResponseEntity changePassword(@PathVariable int empId,@RequestBody User newPass) {
		try {
			return new ResponseEntity(userService.changePassword(empId,newPass),HttpStatus.OK);
		} catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	//to update the user details by the user
	@PutMapping("/{empId}/updatedetails")
	public ResponseEntity updateDetails(@PathVariable int empId,@RequestBody User oldUser) {
		try {
			return new ResponseEntity(userService.updateUserDetails(empId,oldUser),HttpStatus.OK);
		} catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
	}

}
