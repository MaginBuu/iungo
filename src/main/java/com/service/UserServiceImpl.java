package com.service;

import java.util.List;

import com.model.Authorities;
import com.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public List<User> getAllUsers() {
	return userDao.getAllUsers();
	}

	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Transactional
	public void addUser(User user){
		userDao.addUser(user);
	}

	public void addAuthorities(Authorities authorities){ userDao.addAuthorities(authorities);  }
	
	public User getUserById(String userId) {
		return userDao.getUserById(userId);
	}

	public User getUserByEmail(String emailId) {
		return userDao.getUserByEmail(emailId);
	}

	public List<String> getAllUsernames(String username){ return userDao.getAllUsernames(username);}

	public List<User> getQueryResults(String query) { return userDao.getQueryResults(query); }

	public User getAllUserTickets() {
		return userDao.getAllUserTickets();
	}

	public List<User> getAllUsersWithRole(Role role) {
		return userDao.getAllUsersWithRole(role);
	}
}
