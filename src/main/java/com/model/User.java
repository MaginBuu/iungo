package com.model;

import com.model.enums.GenderType;
import com.model.enums.Role;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = "Users.findAll", query = "SELECT c FROM User c"),
		@NamedQuery(name = "Users.findAllWithProcedures", query ="SELECT o FROM User o JOIN FETCH o.procedures i"),
		@NamedQuery(name = "Users.findAllWithTickets", query ="SELECT o FROM User o JOIN FETCH o.tickets i WHERE o.userId =:id"),
		@NamedQuery(name = "Users.findAllWithRoles", query ="SELECT o FROM User o JOIN FETCH o.roles i"),

		//@NamedQuery(name = "Users.findAllWithProcedures", query="SELECT DISTINCT e FROM User e LEFT JOIN FETCH e.procedures t"),
		@NamedQuery(name = "Users.findById", query = "SELECT r FROM User r WHERE r.userId = :id"),
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")
		@NamedQuery(name = "Users.findByEmail", query = "SELECT r FROM User r WHERE r.emailId = :email"),
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

	@Column(name = "PASSWORD")
	private String password;

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

	@Transient
	private String role;

	@OneToMany(mappedBy = "userR", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@MapKey(name="roleKey")
	private Map<Role, RoleClass> roles = new HashMap<>();

	@Column(name = "EMAIL")
	//@NotNull
	private String emailId;

	@Column(name = "GENDER")
	private GenderType gender;

	@Column(name = "LANGUAGE", columnDefinition = "varchar default 'CATALAN'") //ENUM
	private String language;

	@Column(name = "NOTIFICATIONS", columnDefinition = "boolean default TRUE")
	private boolean notificiationsEnabled;


	@OneToMany(mappedBy = "userP", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Procedure> procedures;

	// @OneToMany(targetEntity=Procedure.class, mappedBy="users")
	// //@JoinColumn(name = "PROCEDURE_ID") <---- HO HEM DE POSAR?
	// private List<Procedure> procedures;

    @OneToMany(mappedBy="userT", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "PROCEDURE_ID") <---- HO HEM DE POSAR?
    private List<Ticket> tickets;

	//@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	//private List<CartItem> cartItem;

	//@OneToOne(mappedBy = "users")
	//private Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
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

	public boolean getNotificiationsEnabled() {
		return notificiationsEnabled;
	}

	public void setNotificiationsEnabled(boolean notificiationsEnabled) { this.notificiationsEnabled = notificiationsEnabled; }

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public Map<Role, RoleClass> getRoles() { return roles; }

	public List<Ticket> getTickets() {
		List<Ticket> sortTickets = tickets;
		Collections.sort(sortTickets, new Comparator<Ticket>() {
			@Override
			public int compare(Ticket u1, Ticket u2) {
				return -u1.getCreationDate().compareTo(u2.getCreationDate());
			}
		});
		System.out.println(sortTickets);
		return sortTickets;
	}

	public void addRole(Role roleType, RoleClass role){
		roles.put(roleType, role);

	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	//public Customer getCustomer() {
	//	return customer;
	//}

	//public void setCustomer(Customer customer) {
	//	this.customer = customer;
	//}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	
	}
	public List<Procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}
}
