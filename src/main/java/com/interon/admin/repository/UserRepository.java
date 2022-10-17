package com.interon.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interon.admin.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findAll();
	User findByUserId(String userId);
	User findByEmail(String email);
	User findByUserName(String userName);
}
