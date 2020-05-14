package com.ums.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ums.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.userName like %:username%")
	public List<User> getSearchedUsers(@Param("username") String username);
	
	@Query("select u from User u where u.userName = :username and u.password = :password")
	public User login(@Param("username") String userName, @Param("password") String password);
	
}
