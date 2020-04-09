package com.dao;

import java.util.List;

import com.model.Authorities;
import com.model.RoleTeacher;
import com.model.User;
import com.model.enums.Department;
import com.model.enums.Role;

public interface UserDao {

	List<User> getAllUsers();

	List<User> getAllUsersWithRole(Role role);

	void deleteUser(User user);
	
	void addUser(User user);

	void addAuthorities(Authorities authorities);
	
	User getUserById(String userId);

	User getUserByUsername(String username);

	User getAllUserTickets();

	User getAllUserProcedures();

	User getAllUserRoles(String id);
	
	User getUserByEmail(String email);

	List<String> getAllUsernames(String username);

	List<User> getQueryResults(String query);

	List<User> getStudentsByGroup(String groupId);

	//TEACHER

	List<User> getTeachers();

	List<User> getTeachersByDepartment(String department);

	RoleTeacher getTeacherByIdWithTimelines(String teacherId);



}
