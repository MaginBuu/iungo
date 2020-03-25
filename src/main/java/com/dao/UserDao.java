package com.dao;

import java.util.List;

import com.model.Authorities;
import com.model.User;

public interface UserDao {

	List<User> getAllUsers();

	void deleteUser(String userId);
	
	void addUser(User user);

	void addAuthorities(Authorities authorities);
	
	User getUserById(String userId);

	User getAllUserTickets();

	User getAllUserRoles(String id);
	
	User getUserByEmail(String email);

	List<User> getQueryResults(String query);

}
