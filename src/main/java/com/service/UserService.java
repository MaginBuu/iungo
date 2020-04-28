package com.service;

import java.util.List;

import com.model.*;
import com.model.enums.Role;

public interface UserService {

	List<User> getAllUsers();
	
	void deleteUser(User user);

	void addUser(User user);

	void addAuthorities(Authorities authorities);

	Authorities getAuthoritiesByEmail(String email);

	User getUserById(String userId);

	User getUserByUsername(String username);

	User getAllUserTickets();

	User getAllUserProcedures(String userId);

	List<User> getAllUsersWithRole(Role role);
	
	User getUserByEmail(String emailId);

	List<String> getAllUsernames(String username);

	List<User> getQueryResults(String query);

	List<User> getStudentsByGroup(String groupId);

	//NOTIFICATIONS

	User getAllUserNotifications(String id);

	void addNotification(Notification notification);

	void eraseNotifications(String id);

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

	//STUDENTS

	RoleStudent getStudentWithResponsibles(String userId);

	ClassGroup getGroupByTutor(String tutorId);

	RoleStudent getStudentByUserId(String userId);

	//TUTOR

	List<Evaluation> getEvaluations();

}
