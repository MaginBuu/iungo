package com.service;

import java.util.List;

import com.model.Authorities;
import com.model.User;
import com.model.enums.Role;

public interface UserService {

	List<User> getAllUsers();
	
	void deleteUser(String userId);

	void addUser(User user);

	public void addAuthorities(Authorities authorities);

	User getUserById(String userId);

	User getAllUserTickets();

	List<User> getAllUsersWithRole(Role role);
	
	User getUserByEmail(String emailId);

	List<User> getQueryResults(String query);

}
