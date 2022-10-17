package com.interon.admin.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "userid")
	private String userId;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "nickname")
	private String nickName;
	@Column(name = "email")
	private String email;
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "reenterpassword")
	private String reEnterPassword;
	@Column(name = "phonenumber")
	private String phoneNumber;
	@Column(name = "address1")
	private String address1;
	@Column(name = "address2")
	private String address2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "zipcode")
	private String zipCode;
	@Column(name = "country")
	private String country;
	@Column(name = "gender")
	private String gender;
	@Column(name = "emergencycontactname")
	private String emergencyContactName;
	@Column(name = "emergencycontactrelation")
	private String emergencyContactRelation;
	@Column(name = "emergencycontactemail")
	private String emergencyContactEmail;
	@Column(name = "emergencycontactnumber")
	private String emergencyContactNumber;
	@Column(name = "emergencycontactlocation")
	private String emergencyContactLocation;
	@Column(name = "status")
	private String status = "active";
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user_id", referencedColumnName="userid", unique=false),
	inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="roleid", unique=false))
	private List<Role> roles = new ArrayList<>();

	// no args constructor
	public User() {
		super();
	}
	
	public User(String userId, String firstName, String lastName, String nickName, String email, String userName,
			String password, String reEnterPassword, String phoneNumber, String address1, String address2, String city,
			String state, String zipCode, String country, String gender, String emergencyContactName,
			String emergencyContactRelation, String emergencyContactEmail, String emergencyContactNumber,
			String emergencyContactLocation, String status, List<Role> roles) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.reEnterPassword = reEnterPassword;
		this.phoneNumber = phoneNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.gender = gender;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactRelation = emergencyContactRelation;
		this.emergencyContactEmail = emergencyContactEmail;
		this.emergencyContactNumber = emergencyContactNumber;
		this.emergencyContactLocation = emergencyContactLocation;
		this.status = status;
		this.roles = roles;
	}

	//getters and setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnterPassword() {
		return reEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}

	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}

	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}

	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}

	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getEmergencyContactLocation() {
		return emergencyContactLocation;
	}

	public void setEmergencyContactLocation(String emergencyContactLocation) {
		this.emergencyContactLocation = emergencyContactLocation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}	
	
	
	
}
