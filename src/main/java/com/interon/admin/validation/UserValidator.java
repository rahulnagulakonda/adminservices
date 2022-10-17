package com.interon.admin.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interon.admin.enums.Status;
import com.interon.admin.model.Role;
import com.interon.admin.model.User;
import com.interon.admin.repository.UserRepository;
import com.interon.admin.util.ValidationUtils;

@Component
public class UserValidator {

	@Autowired
	private ValidationStatus validationStatus;

	@Autowired
	private UserRepository userRepo;

	public ValidationStatus isValidForCreate(User user) {

		List<String> listMessages = new ArrayList<>();

		String userId = user.getUserId();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String nickName = user.getNickName();
		String email = user.getEmail();
		String userName = user.getUserName();
		String password = user.getPassword();
		String reEnterPassword = user.getReEnterPassword();
		String phoneNumber = user.getPhoneNumber();
		String address1 = user.getAddress1();
		String address2 = user.getAddress2();
		String city = user.getCity();
		String state = user.getState();
		String zipCode = user.getZipCode();
		String country = user.getCountry();
		String gender = user.getGender();
		String emergencyContactName = user.getEmergencyContactName();
		String emergencyContactRelation = user.getEmergencyContactRelation();
		String emergencyContactEmail = user.getEmergencyContactEmail();
		String emergencyContactNumber = user.getEmergencyContactNumber();
		String emergencyContectLocation = user.getEmergencyContactLocation();
		List<Role> roles = user.getRoles();

		String allChars = "^[a-zA-Z]*$";
		String allNums = "^[0-9]+$";
		String alphaNumeric = "^[a-zA-Z0-9]*$";
		String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		String passwordFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()])(?=\\S+$).{8,20}$";
		
		User findUserByUserId = userRepo.findByUserId(user.getUserId());
		User findUserByEmail = userRepo.findByEmail(user.getEmail());
		
		validationStatus.setStatus(Status.VALID);

		if (findUserByUserId != null) {
			listMessages.add("User already exists with the same User Id");
			validationStatus.setMessages(listMessages);
			validationStatus.setStatus(Status.INVALID);
			return validationStatus;
		}
		else if(findUserByEmail != null){
			listMessages.add("User already exists with the same Email");
			validationStatus.setMessages(listMessages);
			validationStatus.setStatus(Status.INVALID);
			return validationStatus;
		}
		else {
			if (ValidationUtils.isNullString(userId) || !ValidationUtils.regexValidate(userId, alphaNumeric)) {
				listMessages.add("Enter a valid User Id");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(firstName) || !ValidationUtils.regexValidate(firstName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(firstName)) {
				listMessages.add("Enter a valid First Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(lastName) || !ValidationUtils.regexValidate(lastName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(lastName)) {
				listMessages.add("Enter a valid Last Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (!ValidationUtils.regexValidate(nickName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(nickName)) {
				listMessages.add("Enter a valid Nick Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(email) || !ValidationUtils.regexValidate(email, emailFormat)) {
				listMessages.add("Enter a valid Email");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(userName) || !ValidationUtils.regexValidate(userName, alphaNumeric)) {
				listMessages.add("Enter a valid User Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(password) || !ValidationUtils.regexValidate(password, passwordFormat)) {
				listMessages.add(
						"A password must be atleast 8 characters which includes alteast 1 of digit, upper case, lower case, special character and cannot contain white spaces");
				validationStatus.setStatus(Status.INVALID);
			}
			if (!reEnterPassword.matches(password)) {
				listMessages.add(
						"Passwords Doesn't Match");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(phoneNumber) || !ValidationUtils.regexValidate(phoneNumber, allNums)
					|| ValidationUtils.isStringLengthNot10(phoneNumber)) {
				listMessages.add("Enter a valid 10 digit Phone Number");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(address1) || ValidationUtils.isStringLengthMoreThan50(address1)) {
				listMessages.add("Enter a valid Address Line 1");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isStringLengthMoreThan50(address2)) {
				listMessages.add("Enter a valid Address Line 2");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(city) || !ValidationUtils.regexValidate(city, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(city)) {
				listMessages.add("Enter a valid City");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(state) || !ValidationUtils.regexValidate(state, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(state)) {
				listMessages.add("Enter a valid State");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(zipCode) || !ValidationUtils.regexValidate(zipCode, allNums)
					|| !ValidationUtils.isStringLength5or6(zipCode)) {
				listMessages.add("Enter a valid 5 or 6 digit zipcode");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(country)) {
				listMessages.add("Select Country");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(gender)) {
				listMessages.add("Select Gender");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactName) || !ValidationUtils.regexValidate(emergencyContactName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(emergencyContactName)) {
				listMessages.add("Enter a valid Emergency Contact Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactRelation) || !ValidationUtils.regexValidate(emergencyContactRelation, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(emergencyContactRelation)) {
				listMessages.add("Enter a valid Emergency Contact Relation");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactEmail) || !ValidationUtils.regexValidate(emergencyContactEmail, emailFormat)) {
				listMessages.add("Enter a valid Emergency Contact Email");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactNumber) || !ValidationUtils.regexValidate(emergencyContactNumber, allNums)
					|| ValidationUtils.isStringLengthNot10(emergencyContactNumber)) {
				listMessages.add("Enter a valid 10 digit Emergency Contact Number");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContectLocation)) {
				listMessages.add("Select Emergency Contact Location");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isEmptyList(roles)) {
				listMessages.add("Select atleast 1 Role");
				validationStatus.setStatus(Status.INVALID);
			}
			validationStatus.setMessages(listMessages);
			return validationStatus;
		}
	}

	public ValidationStatus isValidForUpdate(User user,String id) {

		List<String> listMessages = new ArrayList<>();

		String userId = user.getUserId();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String nickName = user.getNickName();
		String email = user.getEmail();
		String phoneNumber = user.getPhoneNumber();
		String address1 = user.getAddress1();
		String address2 = user.getAddress2();
		String city = user.getCity();
		String state = user.getState();
		String zipCode = user.getZipCode();
		String country = user.getCountry();
		String gender = user.getGender();
		String emergencyContactName = user.getEmergencyContactName();
		String emergencyContactRelation = user.getEmergencyContactRelation();
		String emergencyContactEmail = user.getEmergencyContactEmail();
		String emergencyContactNumber = user.getEmergencyContactNumber();
		String emergencyContectLocation = user.getEmergencyContactLocation();
		List<Role> roles = user.getRoles();
		
		String allChars = "^[a-zA-Z]*$";
		String allNums = "^[0-9]+$";
		String alphaNumeric = "^[a-zA-Z0-9]*$";
		String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		User findUser = userRepo.findByUserId(id);
		
		validationStatus.setStatus(Status.VALID);

		if (findUser == null) {
			listMessages.add("User does not exist");
			validationStatus.setMessages(listMessages);
			validationStatus.setStatus(Status.INVALID);
			return validationStatus;
		} else {
			if (ValidationUtils.isNullString(userId) || !ValidationUtils.regexValidate(userId, alphaNumeric)) {
				listMessages.add("Enter a valid User Id");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(firstName) || !ValidationUtils.regexValidate(firstName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(firstName)) {
				listMessages.add("Enter a valid First Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(lastName) || !ValidationUtils.regexValidate(lastName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(lastName)) {
				listMessages.add("Enter a valid Last Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (!ValidationUtils.regexValidate(nickName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(nickName)) {
				listMessages.add("Enter a valid Nick Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(email) || !ValidationUtils.regexValidate(email, emailFormat)) {
				listMessages.add("Enter a valid Email");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(phoneNumber) || !ValidationUtils.regexValidate(phoneNumber, allNums)
					|| ValidationUtils.isStringLengthNot10(phoneNumber)) {
				listMessages.add("Enter a valid 10 digit Phone Number");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(address1) || ValidationUtils.isStringLengthMoreThan50(address1)) {
				listMessages.add("Enter a valid Address Line 1");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isStringLengthMoreThan50(address2)) {
				listMessages.add("Enter a valid Address Line 2");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(city) || !ValidationUtils.regexValidate(city, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(city)) {
				listMessages.add("Enter a valid City");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(state) || !ValidationUtils.regexValidate(state, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(state)) {
				listMessages.add("Enter a valid State");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(zipCode) || !ValidationUtils.regexValidate(zipCode, allNums)
					|| !ValidationUtils.isStringLength5or6(zipCode)) {
				listMessages.add("Enter a valid 5 or 6 digit zipcode");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(country)) {
				listMessages.add("Select Country");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(gender)) {
				listMessages.add("Select Gender");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactName) || !ValidationUtils.regexValidate(emergencyContactName, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(emergencyContactName)) {
				listMessages.add("Enter a valid Emergency Contact Name");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactRelation) || !ValidationUtils.regexValidate(emergencyContactRelation, allChars)
					|| ValidationUtils.isStringLengthMoreThan25(emergencyContactRelation)) {
				listMessages.add("Enter a valid Emergency Contact Relation");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactEmail) || !ValidationUtils.regexValidate(emergencyContactEmail, emailFormat)) {
				listMessages.add("Enter a valid Emergency Contact Email");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContactNumber) || !ValidationUtils.regexValidate(emergencyContactNumber, allNums)
					|| ValidationUtils.isStringLengthNot10(emergencyContactNumber)) {
				listMessages.add("Enter a valid 10 digit Emergency Contact Number");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isNullString(emergencyContectLocation)) {
				listMessages.add("Select Emergency Contact Location");
				validationStatus.setStatus(Status.INVALID);
			}
			if (ValidationUtils.isEmptyList(roles)) {
				listMessages.add("Select atleast 1 Role");
				validationStatus.setStatus(Status.INVALID);
			}
			validationStatus.setMessages(listMessages);
			return validationStatus;
			
			
			
		}
	}
}
