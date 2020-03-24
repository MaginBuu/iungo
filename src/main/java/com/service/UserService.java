package com.service;

import java.util.List;

import com.model.Authorities;
import com.model.User;

public interface UserService {

	List<User> getAllUsers();
	
	void deleteUser(String userId);

	void addUser(User user);

	public void addAuthorities(Authorities authorities);

	User getUserById(String userId);

	User getAllUserTickets();

	User getAllUserRoles();
	
	User getUserByEmail(String emailId);

	List<User> getQueryResults(String query);

}
