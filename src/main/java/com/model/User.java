package com.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = "Users.findAll", query = "SELECT c FROM User c"),
		@NamedQuery(name = "Users.findById", query = "SELECT r FROM User r WHERE r.userId = :id"),
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")
})
public class User implements Serializable {

	private static final long serialVersionUID = 2681531852204068105L;
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "NAME")
	//@NotNull
	private String name;

	@Column(name = "SURNAME")
	//@NotNull
	private String surname;

	@Column(name = "SECOND_SURNAME")
	private String secondSurname;

	@Column(name = "BIRTH_DATE")
	//@NotNull
	private String birth;

	@Column(name = "NIF")
	private String nif;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "AUXILIAR_PHONE")
	private String phoneAux;

	@Column(name = "PHOTO")
	private String photoPath;

	@Column(name = "USERNAME")
	//@NotNull
	private String username;

	@Column(name = "ROLE") //ENUM
	private String role;

	@Column(name = "EMAIL")
	//@NotNull
	private String emailId;

	@Column(name = "LANGUAGE", columnDefinition = "varchar default 'CATALAN'") //ENUM
	private String language;

	@Column(name = "NOTIFICATIONS", columnDefinition = "boolean default true")
	private String notificiationsEnabled;


	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
	private List<Procedure> procedures;

	//@OneToOne
	//@JoinColumn(name = "CREDENTIALS", referencedColumnName = "USER")
	//private UserCredentials userCredentials;

	// @OneToMany(targetEntity=Procedure.class, mappedBy="users")
	// //@JoinColumn(name = "PROCEDURE_ID") <---- HO HEM DE POSAR?
	// private List<Procedure> procedures;

    //@OneToMany(targetEntity=Ticket.class, mappedBy="users")
    //@JoinColumn(name = "PROCEDURE_ID") <---- HO HEM DE POSAR?
    //private List<Ticket> tickets;

	//@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	//private List<CartItem> cartItem;

	//@OneToOne(mappedBy = "users")
	//private Customer customer;

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ANTI_BULLYING_REPORT_ID")
	private List<AntiBullyingReport> antiBullyingReports;

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneAux() {
		return phoneAux;
	}

	public void setPhoneAux(String phoneAux) {
		this.phoneAux = phoneAux;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNotificiationsEnabled() {
		return notificiationsEnabled;
	}

	public void setNotificiationsEnabled(String notificiationsEnabled) {
		this.notificiationsEnabled = notificiationsEnabled;
	}

	//public Customer getCustomer() {
	//	return customer;
	//}

	//public void setCustomer(Customer customer) {
	//	this.customer = customer;
	//}
}
