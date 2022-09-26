package com.admin.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.main.model.User;
import com.admin.main.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	//returns the details of a specific user using employeeId
	public User findUserDetails(int empId) throws RuntimeException{
		User findUser = userRepo.findByEmployeeId(empId);
		if(findUser==null) {
			throw new RuntimeException("User not found");
		} else {
			return findUser;
		}
	}
	
	//returns the user details with updated password
	public User changePassword(int empId,User newPass) throws RuntimeException{
		User userPass = userRepo.findByEmployeeId(empId);
		if(userPass==null) {
			throw new RuntimeException("User not found");
		} else {
			if(userPass.getPassword().equals(newPass.getPassword())) {
				throw new RuntimeException("New Password matches with Old Password");
			} else {
				userPass.setPassword(newPass.getPassword());
				userRepo.save(userPass);
				return userPass;
			}
		}
	}
	
	//returns the user details after updating
	public User updateUserDetails(int empId,User updateUser) throws RuntimeException{
		User oldUser = userRepo.findByEmployeeId(empId);
		if(oldUser==null) {
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
