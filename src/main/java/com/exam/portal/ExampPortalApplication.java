package com.exam.portal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.portal.entity.Role;
import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;
import com.exam.portal.service.UserServiceImpl;
import com.exam.portal.servinter.UserService;

@SpringBootApplication
public class ExampPortalApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImpl userService;
	
	public static void main(String[] args) {
		SpringApplication.run(ExampPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
//		System.out.println("Starting code");
//		
//		User use=new User();
//		use.setFirstName("Anant");
//		use.setLastName("Mishra");
//		use.setUserName("Mishra123");
//		use.setPassword("anant123");
//		use.setEmail("abc@gmail.com");
//		use.setProfile("default.png");
//		Role role = new Role();
//		role.setId(44L);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> setRole= new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(use);
//		setRole.add(userRole);
//		
//		User user = this.userService.createUser(use, setRole);
//		System.out.println(user.getUserName());
	}

}
