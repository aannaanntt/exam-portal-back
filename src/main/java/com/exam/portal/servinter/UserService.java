package com.exam.portal.servinter;

import java.util.Set;

import com.exam.portal.entity.User;
import com.exam.portal.entity.UserRole;

public interface UserService {
	
public User createUser(User user,Set<UserRole> userRoles) throws Exception ;

public User getUserByUserName(String userName);

public void deleteUserById(Long id);

}
