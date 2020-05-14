package com.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.model.Role;
import com.ums.service.UserService;

@RestController
@RequestMapping(value="roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin
public class RoleController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/add_role")
	public void addRoles(@RequestBody Role role) {
		userService.addRoles(role);
	}
	
	@GetMapping("/get_roles")
	public List<Role> getAllRoles() {
		 return userService.getAllRoles();	
	}
	
	@DeleteMapping("/delete_role/{roleId}")
	public void deleteRole(@PathVariable("roleId") int roleId) {
		userService.deleteRole(roleId);
	}
	
}
