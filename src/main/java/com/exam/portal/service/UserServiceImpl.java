package com.exam.portal.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;
import com.exam.portal.repo.RoleRepo;
import com.exam.portal.repo.UserRepository;
import com.exam.portal.servinter.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	//creating user
	public User createUser(User user,Set<UserRole> userRoles) throws Exception {
		User use=null;
		if(checkIfUserExists(user)) {
			System.out.println("User is already present");
			
			throw new Exception("User already present");
		}else {
			
			for(UserRole ur : userRoles) {
				roleRepo.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			use=this.userRepo.save(user);
			
		}
		
		return use;
		
	}
	
	
	public boolean checkIfUserExists(User user) {
		return	userRepo.findByUserName(user.getUserName()).isPresent();

		
	}


	@Override
	public User getUserByUserName(String userName) {
		
		return userRepo.findByUserName(userName).get();
	}




	@Override
	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
		
	}

}
