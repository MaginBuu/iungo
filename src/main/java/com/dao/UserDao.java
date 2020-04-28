package com.dao;

import java.util.List;

import com.model.*;
import com.model.enums.Department;
import com.model.enums.Role;

public interface UserDao {

	List<User> getAllUsers();

	List<User> getAllUsersWithRole(Role role);

	void deleteUser(User user);
	
	void addUser(User user);

	void addAuthorities(Authorities authorities);

	Authorities getAuthoritiesByEmail(String email);
	
	User getUserById(String userId);

	User getUserByUsername(String username);

	User getAllUserTickets();

	User getAllUserProcedures(String userId);

	User getAllUserRoles(String id);
	
	User getUserByEmail(String email);

	List<String> getAllUsernames(String username);

	List<User> getQueryResults(String query);

	List<User> getStudentsByGroup(String groupId);

	//NOTIFICATIONS

	void eraseNotifications(String id);

	User getAllUserNotifications(String id);

	void addNotification(Notification notification);

	//TEACHER

	List<User> getTeachers();

	List<User> getTeachersByDepartment(String department);

	RoleTeacher getTeacherByIdWithTimelines(String teacherId);

	RoleTeacher getTeacherByIdWithSubjects(String teacherId);

	RoleTeacher getTeacherById(String teacherId);

	List<RoleTeacher> getAllTeachers();

	//RESPONSIBLES

	List<RoleResponsible> getStudentsResponsibles(List<RoleStudent> students);

	List<RoleResponsible> getAllResponsibles();

	//STUDENT

	RoleStudent getStudentWithResponsibles(String userId);

	ClassGroup getGroupByTutor(String tutorId);

	RoleStudent getStudentByUserId(String userId);

	//TUOTOR

	List<Evaluation> getEvaluations();


}
