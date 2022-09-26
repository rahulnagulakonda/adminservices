package com.admin.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.main.model.*;
import com.admin.main.repository.*;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRoleMappingRepository urmRepo;
	
	//returns all the users present, irrespective of the status of the user
	public List<User> findAllUsers() throws RuntimeException{
		List<User> allUsers = userRepo.findAll();
		if(allUsers== null) {
			throw new RuntimeException("No Users Found!!!");
		} else {
			return allUsers;
		}
	}
	
	//returns all the roles 
	public List<Role> findAllRoles() throws RuntimeException{
		List<Role> allRoles = roleRepo.findAll();
		if(allRoles==null) {
			throw new RuntimeException("No Roles Found!!!");
		} else {
			return allRoles;
		}
	}
	
	//returns all the roles specified to the users
	public List<UserRoleMapping> findAllUserRoles() throws RuntimeException{
		List<UserRoleMapping> allRoles = urmRepo.findAll();
		if(allRoles==null) {
			throw new RuntimeException("No Roles Found!!!");
		} else {
			return allRoles;
		}
	}
	
	//returns the details of the created user
	public User createNewUser(User user) throws RuntimeException{
		User findUser = userRepo.findByEmail(user.getEmail());
		if(findUser!=null) {
			throw new RuntimeException("User Already Exists with Same Email");
		} else {
			user.setStatus("active");
			userRepo.save(user);
			return user;
		}
	}
	
	//returns the details of the user after updating 
	public User updateUser(User user,int id) throws RuntimeException{
		User oldUser = userRepo.findByEmployeeId(id);
		if(oldUser==null) {
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
		}
		userRepo.save(oldUser);
		return oldUser;
	}
	
	//returns the details of the user after setting status to deactivated
	public User deactiveUser(int empId) throws RuntimeException{
		User userById = userRepo.findByEmployeeId(empId);
		if(userById==null) {
			throw new RuntimeException("User Doesn't Exist");
		} else {
			if(userById.getStatus().equals("deactivated")){
				throw new RuntimeException("User is Already Deactivated");
			} else {
				userById.setStatus("deactivated");
				userRepo.save(userById);
				return userById;
			}
		}
	}
}
