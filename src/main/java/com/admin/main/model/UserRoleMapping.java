package com.admin.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRoleMapping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mappingid")
	private int mappingId;
	@Column(name="employeeid")
	private int employeeId;
	@Column(name="roleid")
	private int roleId;
	
	//No Args Constructor
	public UserRoleMapping() {
		super();
	}
	
	//All Args Constructor
	public UserRoleMapping(int mappingId, int employeeId, int roleId) {
		super();
		this.mappingId = mappingId;
		this.employeeId = employeeId;
		this.roleId = roleId;
	}
	
	//getters and setters
	public int getMappingId() {
		return mappingId;
	}

	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
}
