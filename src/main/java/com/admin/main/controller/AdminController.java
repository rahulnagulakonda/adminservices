package com.admin.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.main.model.*;
import com.admin.main.service.AdminService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;
	
	//to view all the users
	@GetMapping("/allusers")
	public ResponseEntity getAllUsers(){
		try {
		return new ResponseEntity(adminService.findAllUsers(),HttpStatus.OK);
		}
		catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	//to create a user
	@PostMapping("/createuser")
	public ResponseEntity createNewUser(@RequestBody User user) {
		try {
			return new ResponseEntity(adminService.createNewUser(user),HttpStatus.OK);
		} catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
		
	}
	
	//to update a user
	@PutMapping("/updateuser/{id}")
	public ResponseEntity updateUser(@PathVariable int id,@RequestBody User user) {
		try {
			return new ResponseEntity(adminService.updateUser(user,id),HttpStatus.OK);
		} catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
		
	}
	
	//to deactivate a user
	@DeleteMapping("/deactivateuser/{empId}")
	public ResponseEntity deactiveUser(@PathVariable int empId) {
		try {
		return new ResponseEntity(adminService.deactiveUser(empId),HttpStatus.OK);
		} catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	//to view all the roles
	@GetMapping("/allroles")
	public ResponseEntity getAllRoles(){
		try {
		return new ResponseEntity(adminService.findAllRoles(),HttpStatus.OK);
		}
		catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
	//to view the user role mapping
	@GetMapping("/alluserroles")
	public ResponseEntity getAllUserRoles(){
		try {
		return new ResponseEntity(adminService.findAllUserRoles(),HttpStatus.OK);
		}
		catch(RuntimeException re) {
			return new ResponseEntity(re.getMessage(),HttpStatus.CONFLICT);
		}
	}
	
}
