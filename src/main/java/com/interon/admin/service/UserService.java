package com.interon.admin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interon.admin.enums.Status;
import com.interon.admin.model.Role;
import com.interon.admin.model.User;
import com.interon.admin.repository.RoleRepository;
import com.interon.admin.repository.UserRepository;
import com.interon.admin.validation.UserValidator;
import com.interon.admin.validation.ValidationStatus;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserValidator userValidator;

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

	// returns the details of the created role
	public void createNewRole(Role newRole) throws RuntimeException {
		Role findRole = roleRepo.findByRoleName(newRole.getRoleName());
		if (findRole != null) {
			throw new RuntimeException("Role already exists");
		} else {
			roleRepo.save(newRole);
		}
	}

	// returns the details of the created user
	public ValidationStatus createNewUser(User newUser) throws RuntimeException {
		ValidationStatus validUser = userValidator.isValidForCreate(newUser);
		if(validUser.getStatus()==Status.INVALID) {
			return validUser;
		} else {
			userRepo.save(newUser);
			validUser.getMessages().add("User Successfully Saved");
			return validUser;
		}
	}

	// returns the details of the user after updating
	public ValidationStatus updateUser(User user, String id) throws RuntimeException {
		ValidationStatus validUser = userValidator.isValidForUpdate(user,id);
		if(validUser.getStatus()==Status.INVALID) {
			return validUser;
		} else {
			User oldUser = userRepo.findByUserId(id);
			oldUser.setUserId(user.getUserId());
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			oldUser.setNickName(user.getNickName());
			oldUser.setEmail(user.getEmail());
			oldUser.setPhoneNumber(user.getPhoneNumber());
			oldUser.setAddress1(user.getAddress1());
			oldUser.setAddress2(user.getAddress2());
			oldUser.setCity(user.getCity());
			oldUser.setState(user.getState());
			oldUser.setZipCode(user.getZipCode());
			oldUser.setCountry(user.getCountry());
			oldUser.setGender(user.getGender());
			oldUser.setEmergencyContactName(user.getEmergencyContactName());
			oldUser.setEmergencyContactRelation(user.getEmergencyContactRelation());
			oldUser.setEmergencyContactNumber(user.getEmergencyContactNumber());
			oldUser.setEmergencyContactEmail(user.getEmergencyContactEmail());
			oldUser.setEmergencyContactLocation(user.getEmergencyContactLocation());
			oldUser.setRoles(user.getRoles());
			userRepo.save(oldUser);
			validUser.getMessages().add("User Successfully Updated");
			return validUser;
		}
	}

	// returns the details of the user after setting status to deactivated
	public User deactiveUser(String userId) throws RuntimeException {
		User userById = userRepo.findByUserId(userId);
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
	public User findUserDetails(String userId) throws RuntimeException {
		User findUser = userRepo.findByUserId(userId);
		if (findUser == null) {
			throw new RuntimeException("User not found");
		} else {
			return findUser;
		}
	}

	// returns the user details with updated password
	public User changePassword(String userId, User newPass) throws RuntimeException {
		User userPass = userRepo.findByUserId(userId);
		if (userPass == null) {
			throw new RuntimeException("User not found");
		} else {
			if (userPass.getPassword().equals(newPass.getPassword())) {
				throw new RuntimeException("New Password matches with Old Password");
			} else if(!newPass.getPassword().equals(newPass.getReEnterPassword())) {
				throw new RuntimeException("Passwords doesn't match");
			} else {
				userPass.setPassword(newPass.getPassword());
				userPass.setReEnterPassword(newPass.getReEnterPassword());
				userRepo.save(userPass);
				return userPass;
			}
		}
	}

}
