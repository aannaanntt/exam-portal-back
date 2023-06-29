package com.exam.portal.service;

import java.util.Optional;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.exam.portal.entity.User;
import com.exam.portal.repo.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository user;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> name = this.user.findByUserName(username);
		
		if(!name.isPresent()) {
			System.out.println("User is not presnt");
			throw new UsernameNotFoundException("User Name is not present");
		}
		return name.get();
	}

}
