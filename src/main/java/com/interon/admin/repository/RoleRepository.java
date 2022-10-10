package com.interon.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interon.admin.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	List<Role> findAll();
	Role findByRoleName(String roleName);
}
