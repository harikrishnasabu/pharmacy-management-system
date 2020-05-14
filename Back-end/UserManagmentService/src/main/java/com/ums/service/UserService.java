package com.ums.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.dao.RoleRepository;
import com.ums.dao.UserRepository;
import com.ums.dto.UserData;
import com.ums.model.Role;
import com.ums.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	// for creating the admin initially
	public void createAdmin() {
		User user = new User();
		user.setUserName("Admin");
		user.setEmail("admin@gmail.com");

		Role role = new Role();
		role.setRoleName("Admin");
		user.setRole(role);

		userRepository.save(user);
	}

	public User login(String userName, String password) {
		return userRepository.login(userName, password);
	}

	/**
	 * to create or update user params: user details to be saved or updated returns:
	 * string to denotes user is successfully saved
	 */
	public void createUsers(UserData userData) {

		Role role = roleRepository.getOne(userData.getRoleId());
		Optional<User> optionalUser = null;
		if (userData.getUserId() != 0) {

			optionalUser = userRepository.findById(userData.getUserId());
		} else {

			optionalUser = Optional.of(new User());

		}

		optionalUser.ifPresent((user) -> {
			if (!"".equals(userData.getUserName())) {
				user.setUserName(userData.getUserName());
			}
			if (!"".equals(userData.getEmail())) {
				user.setEmail(userData.getEmail());
			}
			if (!"".equals(userData.getPassword())) {
				user.setPassword(userData.getPassword());
			}
			if (!"".equals(userData.getContactNo())) {
				user.setContactNo(userData.getContactNo());
			}
			if (!"".equals(userData.getAddress())) {
				user.setAddress(userData.getAddress());
			}

			user.setRole(role);
			userRepository.save(user);

		});

	}

	/**
	 * 
	 * To create or update role string to denotes user is successfully saved
	 * 
	 * @param role
	 */
	public void addRoles(Role role) {
		if (!"".equals(role.getRoleId()) && roleRepository.findById(role.getRoleId()).isPresent()) {
			Role updatedRole = roleRepository.findById(role.getRoleId()).get();
			updatedRole.setRoleName(role.getRoleName());
			roleRepository.save(updatedRole);
		} else {
			roleRepository.save(role);
		}
	}

	/*
	 * to get all roles returns: all role details
	 */
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	/*
	 * to get all users returns: all user details
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/*
	 * search for users params: user name to be searched returns: user details of
	 * user having searched username
	 */
	public List<User> getSearchedUsers(String userName) {
		return userRepository.getSearchedUsers(userName);
	}
	
	/**
	 * select user by userId
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId) {
		if(userRepository.findById(userId).isPresent()) {
			return userRepository.findById(userId).get();
		} else {
			return new User();
		}
	}

	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}

	public void deleteRole(int roleId) {
		roleRepository.deleteById(roleId);
	}
}
