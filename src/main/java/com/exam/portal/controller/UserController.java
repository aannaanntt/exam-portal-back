package com.exam.portal.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;
import com.exam.portal.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	
	@Autowired
	private UserServiceImpl useService;
	
	
	@RequestMapping(method = RequestMethod.POST,path = "/")
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception{
		
		user.setProfile("default.png"); 
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setId(45L);
		role.setRoleName("NORMAL");
		UserRole roless = new UserRole();
		roless.setUser(user);
		roless.setRole(role);
		
		roles.add(roless);
		
		this.useService.createUser(user, roles);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.useService.getUserByUserName(username);
		
	}
	
	//add delete method also
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.useService.deleteUserById(userId);
		
	}

}
