package com.ums.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ums.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
