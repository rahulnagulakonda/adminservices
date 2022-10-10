package com.interon.admin.controller;

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

import com.interon.admin.model.User;
import com.interon.admin.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	// to view all the users
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers() {
		try {
			return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// to create a user
	@PostMapping("/createUser")
	public ResponseEntity<Object> createNewUser(@RequestBody User newUser) {
		try {
			userService.createNewUser(newUser);
			return new ResponseEntity<>("User succesfully saved", HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.CONFLICT);
		}

	}

	// to update a user
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.CONFLICT);
		}

	}

	// to deactivate a user
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<Object> deactiveUser(@PathVariable int userId) {
		try {
			return new ResponseEntity<>(userService.deactiveUser(userId), HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.CONFLICT);
		}
	}

	// to view all the roles
	@GetMapping("/roles")
	public ResponseEntity<Object> getAllRoles() {
		try {
			return new ResponseEntity<>(userService.findAllRoles(), HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.CONFLICT);
		}
	}

	// to view the user details
	@GetMapping("/profile/{userId}")
	public ResponseEntity<Object> getUserDetails(@PathVariable int userId) {
		try {
			return new ResponseEntity<>(userService.findUserDetails(userId), HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.CONFLICT);
		}
	}

	// to change password for user login
	@PostMapping("/changePassword/{userId}")
	public ResponseEntity<Object> changePassword(@PathVariable int userId, @RequestBody User newPass) {
		try {
			return new ResponseEntity<>(userService.changePassword(userId, newPass), HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.CONFLICT);
		}
	}

	// to update the user details by the user
	@PutMapping("/updateDetails/{userId}")
	public ResponseEntity<Object> updateDetails(@PathVariable int userId, @RequestBody User oldUser) {
		try {
			return new ResponseEntity<>(userService.updateUserDetails(userId, oldUser), HttpStatus.OK);
		} catch (RuntimeException re) {
			return new ResponseEntity<>(re.getMessage(), HttpStatus.CONFLICT);
		}
	}

}
