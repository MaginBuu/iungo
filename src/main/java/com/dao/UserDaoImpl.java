package com.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.model.*;
import com.model.enums.Department;
import com.model.enums.KeyRoleValue;
import com.model.enums.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.awt.image.ImageWatched;

import javax.servlet.http.HttpServletRequest;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();
		List<User> users =  session.getNamedQuery("Users.findAll").list();
	 	for(User u : users){
			System.out.println(u.getName());
		}
		session.close();
		return users;
	}

	public List<User> getAllUsersWithRole(Role role) {
		Session session = sessionFactory.openSession();
		List<User> users;
		//List<User> users =  session.getNamedQuery("RoleClass.findAllWithRole").setParameter("role", role).list();
		switch (role){
			case RESPONSIBLE:
				users =  session.getNamedQuery("RoleResponsible.getAll").list();
				break;
			case STUDENT:
				users =  session.getNamedQuery("RoleStudent.getAll").list();
				break;
			case TEACHER:
				users =  session.getNamedQuery("RoleTeacher.getAll").list();
				break;
			case ADMIN:
				users =  session.getNamedQuery("RoleAdmin.getAll").list();
				break;
			case SECRETARY:
				users =  session.getNamedQuery("RoleSecretary.getAll").list();
				break;
			default:
				users =  session.getNamedQuery("RoleClass.findAllWithRole").setParameter("role", role).list();
				break;

		}
		session.close();
		return users;
	}


	public User getAllUserTickets() {
		Session session = sessionFactory.openSession();
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//User user = (User)request.getSession().getAttribute("user");
		User userWithTicket = (User) session.getNamedQuery("Users.findAllWithTickets").setParameter("id", "1" ).uniqueResult(); //user.getUserId()
		session.close();
		return userWithTicket;
	}

	public User getAllUserProcedures(String userId) {
		Session session = sessionFactory.openSession();
		User userWithProcedures = (User) session.getNamedQuery("Users.findAllWithProcedures").setParameter("id", userId ).uniqueResult(); //user.getUserId()
		session.close();
		return userWithProcedures;
	}

	public User getAllUserRoles(String id) {
		Session session = sessionFactory.openSession();
		User userWithRoles = (User) session.getNamedQuery("Users.findAllWithRoles").setParameter("id", id).uniqueResult(); //user.getUserId()
		session.close();
		return userWithRoles;
	}

	public void deleteUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		}catch(Exception e){
			if(tx != null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}
	}

	public void addAuthorities(Authorities authorities){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(authorities);
			tx.commit();
		}catch(Exception e){
			if(tx != null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}

	}

	public Authorities getAuthoritiesByEmail(String email) {
		Session session = sessionFactory.openSession();
		Authorities auth = (Authorities) session.getNamedQuery("Authorities.getByEmail").setParameter("emailId", email ).uniqueResult();
		session.close();
		return auth;
	}

	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Authorities authorities = new Authorities();
		System.out.println(user);

		try{
			tx = session.beginTransaction();
			session.saveOrUpdate(user);
			tx.commit();
		}catch(Exception e){
			if(tx != null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}

		System.out.println(user.toString());

	}

	public User getUserById(String userId) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		// select * from Product where isbn=i
		// if we call get method,Record does not exist it will return null
		// if we call load, if the record doesnt exist it will throw exception
		User user = (User) session.get(User.class, userId);
		session.close();
		return user;
	}

	public User getUserByUsername(String username) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		User user = (User) session.getNamedQuery("Users.findByUsername").setParameter("username", username).uniqueResult();
		session.close();
		return user;

	}


	public User getUserByEmail(String email) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		List<User> users = session.getNamedQuery("Users.findByEmail").setParameter("email", email).list();
		session.close();
		return users.get(0);

	}

	public User getUserByRoleId(String roleId) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		User users = (User) session.getNamedQuery("RoleClass.getUserByRoleID").setParameter("roleId", roleId).uniqueResult();
		session.close();
		return users;
	}

	/**
	 *
	 * @param username
	 * @return All usernames equal to the param or with some numbers on it. The non numeric part is the same
	 */
	public List<String> getAllUsernames(String username){
		Session session = sessionFactory.openSession();
		List<String> usernames = session.getNamedQuery("Users.findAllByUsername").setParameter("username", "%" + username + "%").list();
		List<String> usernamesTemp = new LinkedList<>();
		for (String usernameT : usernames){
			if((usernameT.equals(username) || usernameT.matches("" + username + "[0-9]{2}")))
				usernamesTemp.add(usernameT);
		}
		session.close();
		return usernamesTemp;
	}

	public List<User> getQueryResults(String query){
		Session session = sessionFactory.openSession();
		List<User> users = session.createQuery(query).list();
		session.close();
		return users;
	}

	public List<User> getStudentsByGroup(String groupId){
		Session session = sessionFactory.openSession();
		List<User> students = session.getNamedQuery("RoleStudents.findStudentsByGroup").setParameter("groupId", groupId).list();
		System.out.println("groupId" + groupId);
		session.close();
		return students;
	}

	//-------------------- NOTIFICATIONS --------------------

	public void addNotification(Notification notification){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.saveOrUpdate(notification);
			tx.commit();
		}catch(Exception e){
			if(tx != null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}

	}

	public User getAllUserNotifications(String id) {
		Session session = sessionFactory.openSession();
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//User user = (User)request.getSession().getAttribute("user");
		User userWithRoles = (User) session.getNamedQuery("Users.findAllWithNotifications").setParameter("id", id).uniqueResult(); //user.getUserId()
		session.close();
		return userWithRoles;
	}

	public void eraseNotifications(String id) {
		Session session = sessionFactory.openSession();
		try{
			session.getNamedQuery("Notifications.deleteFromUser").setParameter("id", id).executeUpdate(); //user.getUserId()
		}catch(Exception e){
			throw e;
		}finally {
			session.close();
		}

	}

	//-------------------- TEACHER --------------------

	public List<User> getTeachers(){
		Session session = sessionFactory.openSession();
		List<User> teachers = session.getNamedQuery("Users.findTeachers").list();
		session.close();
		return teachers;
	}


	public List<User> getTeachersByDepartment(String department){
		Session session = sessionFactory.openSession();
		List<User> teachers = session.getNamedQuery("Users.findTeacherByDepartment").setParameter("department", Department.valueOf(department)).list();
		session.close();
		return teachers;
	}

	public RoleTeacher getTeacherByIdWithTimelines(String teacherId){
		Session session = sessionFactory.openSession();
		RoleTeacher teacher = (RoleTeacher) session.getNamedQuery("RoleTeacher.findByIdWithTimelines").setParameter("id", teacherId).uniqueResult();
		session.close();
		return teacher;
	}

	public RoleTeacher getTeacherByIdWithSubjects(String teacherId){
		Session session = sessionFactory.openSession();
		RoleTeacher teacher = (RoleTeacher) session.getNamedQuery("RoleTeacher.findByIdWithSubjects").setParameter("id", teacherId).uniqueResult();
		session.close();
		return teacher;
	}

	public RoleTeacher getTeacherById(String teacherId){
		Session session = sessionFactory.openSession();
		RoleTeacher teacher = (RoleTeacher) session.getNamedQuery("RoleTeacher.findById").setParameter("id", teacherId).uniqueResult();
		session.close();
		return teacher;
	}

	public List<RoleTeacher> getAllTeachers(){
		Session session = sessionFactory.openSession();
		List<RoleTeacher> teachers = session.getNamedQuery("RoleTeacher.findAll").list();
		session.close();
		return teachers;
	}





	//-------------------- RESPONSIBLES --------------------


	public List<RoleResponsible> getStudentsResponsibles(List<String> students){
		Session session = sessionFactory.openSession();
		List<RoleResponsible> responsibles = session.getNamedQuery("RoleResponsible.getResponsibles").setParameterList("students", students).list();
		session.close();
		return responsibles;
	}

	public List<RoleResponsible> getStudentResponsibles(String userId){
		Session session = sessionFactory.openSession();
		List<RoleResponsible> responsibles = session.getNamedQuery("RoleStudent.getResponsibles").setParameter("userId", userId).list();
		session.close();
		return responsibles;
	}

	public List<RoleResponsible> getAllResponsibles() {
		Session session = sessionFactory.openSession();
		List<RoleResponsible> responsibles =  session.getNamedQuery("RoleResponsible.getAllRoleResponsible").list();
		session.close();
		return responsibles;
	}

	public List<RoleStudent> getResponsibleChildList(String responsibleId){
		Session session = sessionFactory.openSession();
		RoleResponsible responsible = (RoleResponsible) session.getNamedQuery("RoleResponsible.getChildren").setParameter("id", responsibleId).uniqueResult();
		List<RoleStudent> children =  responsible.getChild();
		session.close();
		return children;
	}


	//-------------------- STUDENTS --------------------


	public RoleStudent getStudentWithResponsibles(String userId) {
		Session session = sessionFactory.openSession();
		RoleStudent student =  (RoleStudent) session.getNamedQuery("RoleStudent.getWithParents").setParameter("userId", userId).uniqueResult();
		session.close();
		return student;
	}

	public ClassGroup getGroupByTutor(String tutorId){
		Session session = sessionFactory.openSession();
		RoleTutor tutor = (RoleTutor) session.getNamedQuery("RoleTutor.findByIdWithGroup").setParameter("tutorId", tutorId).uniqueResult();
		ClassGroup group =  (ClassGroup) session.getNamedQuery("ClassGroup.findByIdWithStudents").setParameter("id", tutor.getGroup().getGroupId()).uniqueResult();
		session.close();
		return group;
	}

	public RoleStudent getStudentByUserId(String userId) {
		Session session = sessionFactory.openSession();
		RoleStudent student =  (RoleStudent) session.getNamedQuery("RoleStudent.getById").setParameter("id", userId).uniqueResult();
		session.close();
		return student;
	}

	//-------------------- TUTOR --------------------

	public List<Evaluation> getEvaluations(){
		Session session = sessionFactory.openSession();
		List<Evaluation> evaluations =  session.getNamedQuery("Evaluation.findAll").list();
		session.close();
		return evaluations;
	}

	public void addUserSubject(UserSubject userSubject){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.saveOrUpdate(userSubject);
			tx.commit();
		}catch(Exception e){
			if(tx != null) tx.rollback();
			throw e;
		}finally {
			session.close();
		}

	}

	//-------------------- KeyRole --------------------

	public User getKeyRoleUser(KeyRoleValue keyRole){
		Session session = sessionFactory.openSession();
		User user =  (User)session.getNamedQuery("KeyRole.getUserByKeyRole").setParameter("keyRole", keyRole).uniqueResult();
		session.close();
		return user;
	}

}

