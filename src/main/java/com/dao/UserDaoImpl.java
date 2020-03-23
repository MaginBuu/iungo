package com.dao;

import java.util.List;

import com.model.Authorities;
import com.model.Procedure;
import com.model.Ticket;
import com.model.enums.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

	public User getAllUserTickets() {
		Session session = sessionFactory.openSession();
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//User user = (User)request.getSession().getAttribute("user");
		User userWithTicket = (User) session.getNamedQuery("Users.findAllWithTickets").setParameter("id", "ff80808170ed37140170ed37ca590001" ).uniqueResult(); //user.getUserId()
		session.close();
		return userWithTicket;
	}

	public void deleteUser(String userId) {
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, userId);
		session.saveOrUpdate(user);
		session.flush();
		session.close();// close the session
	}

	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Authorities authorities = new Authorities();
		System.out.println(user);
		String roles[] = user.getRole().split(",");
		authorities.setAuthorities(roles[roles.length-1]);
		authorities.setEmailId(user.getEmailId());
		try{
			tx = session.beginTransaction();
			session.save(authorities);
			session.save(user);
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

	public User getUserByEmail(String email) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		List<User> users = session.getNamedQuery("Users.findByEmail").setParameter("email", email).list();
		return users.get(0);

	}

	public List<User> getQueryResults(String query){
		Session session = sessionFactory.openSession();
		List<User> users = session.createQuery(query).list();
		return users;
	}
}
