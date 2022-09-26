package com.admin.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findAll();
	User findByEmployeeId(int employeeId);
	User findByEmail(String email);
	User findByUserName(String userName);
}
