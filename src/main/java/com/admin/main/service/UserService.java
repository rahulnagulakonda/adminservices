package com.admin.main.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.main.model.Role;
import com.admin.main.model.User;
import com.admin.main.repository.RoleRepository;
import com.admin.main.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	// returns all the users present, irrespective of the status of the user
	public List<User> findAllUsers() throws RuntimeException {
		List<User> allUsers = userRepo.findAll();
		if (allUsers.isEmpty()) {
			throw new RuntimeException("No Users Found!!!");
		} else {
			return allUsers;
		}
	}

	// returns all the roles
	public List<Role> findAllRoles() throws RuntimeException {
		List<Role> allRoles = roleRepo.findAll();
		if (allRoles.isEmpty()) {
			throw new RuntimeException("No Roles Found!!!");
		} else {
			return allRoles;
		}
	}

	// returns the details of the created user
	public void createNewUser(User newUser) throws RuntimeException {

		User findUser = userRepo.findByEmail(newUser.getEmail());

		if (findUser != null) {
			throw new RuntimeException("User Already Exists with Same Email");
		} else {
			userRepo.save(newUser);
		}
	}

	// returns the details of the user after updating
	public User updateUser(User user, int id) throws RuntimeException {
		User oldUser = userRepo.findByUserId(id);
		if (oldUser == null) {
			throw new RuntimeException("User Not Found");
		} else {
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			oldUser.setEmail(user.getEmail());
			oldUser.setPhoneNumber(user.getPhoneNumber());
			oldUser.setAddress1(user.getAddress1());
			oldUser.setAddress2(user.getAddress2());
			oldUser.setCity(user.getCity());
			oldUser.setState(user.getState());
			oldUser.setZipCode(user.getZipCode());
			oldUser.setCountry(user.getCountry());
			oldUser.setRelationshipStatus(user.getRelationshipStatus());
			oldUser.setEmergencyContactPerson(user.getEmergencyContactPerson());
			oldUser.setEmergencyContactNumber(user.getEmergencyContactNumber());
			oldUser.setStatus(user.getStatus());
			oldUser.setRoles(user.getRoles());
		}
		userRepo.save(oldUser);
		return oldUser;
	}

	// returns the details of the user after setting status to deactivated
	public User deactiveUser(int empId) throws RuntimeException {
		User userById = userRepo.findByUserId(empId);
		if (userById == null) {
			throw new RuntimeException("User Doesn't Exist");
		} else {
			if (userById.getStatus().equals("deactivated")) {
				throw new RuntimeException("User is Already Deactivated");
			} else {
				userById.setStatus("deactivated");
				userRepo.save(userById);
				return userById;
			}
		}
	}

	// returns the details of a specific user using employeeId
	public User findUserDetails(int empId) throws RuntimeException {
		User findUser = userRepo.findByUserId(empId);
		if (findUser == null) {
			throw new RuntimeException("User not found");
		} else {
			return findUser;
		}
	}

	// returns the user details with updated password
	public User changePassword(int empId, User newPass) throws RuntimeException {
		User userPass = userRepo.findByUserId(empId);
		if (userPass == null) {
			throw new RuntimeException("User not found");
		} else {
			if (userPass.getPassword().equals(newPass.getPassword())) {
				throw new RuntimeException("New Password matches with Old Password");
			} else {
				userPass.setPassword(newPass.getPassword());
				userRepo.save(userPass);
				return userPass;
			}
		}
	}

	// returns the user details after updating
	public User updateUserDetails(int empId, User updateUser) throws RuntimeException {
		User oldUser = userRepo.findByUserId(empId);
		if (oldUser == null) {
			throw new RuntimeException("User not found");
		} else {
			oldUser.setFirstName(updateUser.getFirstName());
			oldUser.setLastName(updateUser.getLastName());
			oldUser.setEmail(updateUser.getEmail());
			oldUser.setUserName(updateUser.getUserName());
			oldUser.setPhoneNumber(updateUser.getPhoneNumber());
			oldUser.setAddress1(updateUser.getAddress1());
			oldUser.setAddress2(updateUser.getAddress2());
			oldUser.setCity(updateUser.getCity());
			oldUser.setState(updateUser.getState());
			oldUser.setZipCode(updateUser.getZipCode());
			oldUser.setCountry(updateUser.getCountry());
			oldUser.setRelationshipStatus(updateUser.getRelationshipStatus());
			oldUser.setEmergencyContactPerson(updateUser.getEmergencyContactPerson());
			oldUser.setEmergencyContactNumber(updateUser.getEmergencyContactNumber());
		}
		userRepo.save(oldUser);
		return oldUser;
	}

}
