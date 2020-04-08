package com.model;

import com.model.enums.Department;
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
		@NamedQuery(name = "Users.findAllWithTickets", query ="SELECT o FROM User o JOIN FETCH o.tickets i WHERE o.userId =:id"),
		@NamedQuery(name = "Users.findAllWithProcedures", query ="SELECT o FROM User o JOIN FETCH o.procedures i WHERE o.userId =:id AND i.status = 0"),
		@NamedQuery(name = "Users.findAllWithRoles", query ="SELECT o FROM User o JOIN FETCH o.roles i WHERE o.userId =:id"),
		@NamedQuery(name = "Users.findAllWithRole", query ="SELECT o FROM User o WHERE o.userId IN (SELECT i.userR FROM RoleClass i WHERE i.roleKey =:role)"),
		@NamedQuery(name = "Users.findAllByUsername", query ="SELECT o.username FROM User o WHERE o.username LIKE :username ORDER BY o.username ASC"),

		//@NamedQuery(name = "Users.findAllWithProcedures", query="SELECT DISTINCT e FROM User e LEFT JOIN FETCH e.procedures t"),
		@NamedQuery(name = "Users.findById", query = "SELECT r FROM User r WHERE r.userId = :id"),
//     @NamedQuery(name = "Room.findById", query = "SELECT r,te.email FROM Room r  "
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")
		@NamedQuery(name = "Users.findByEmail", query = "SELECT r FROM User r WHERE r.emailId =:email"),
        @NamedQuery(name = "Users.findByUsername", query = "SELECT r FROM User r WHERE r.username =:username"),
		@NamedQuery(name = "Users.findTeachers", query = "SELECT u FROM User u WHERE u.userId IN(SELECT i.userR FROM RoleClass i WHERE i.roleKey = 1)"),
		@NamedQuery(name = "Users.findTeacherByDepartment", query = "SELECT u FROM User u WHERE u.userId IN(SELECT i.userR FROM RoleClass i WHERE i.roleKey = 1 AND i.roleId IN(SELECT n.roleId FROM RoleTeacher n WHERE n.department =:department))"),
		@NamedQuery(name = "Users.findStudentsByGroup", query = "SELECT u FROM User u WHERE u.userId IN(SELECT i.userR FROM RoleClass i WHERE i.roleKey = 0 AND i.roleId IN(SELECT n.roleId FROM RoleStudent n WHERE n.group.groupId =:groupId ))"),

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

	@OneToMany(mappedBy = "userR", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
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

	@Transient
	private Department department;

	/*@ManyToMany(mappedBy="users", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Conversation> conversations = new LinkedList<>();*/

	@OneToMany(mappedBy = "user", targetEntity = ConversationUser.class)
	private List<ConversationUser> userConversations;

	public User() { }

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

	public RoleClass getRoleClass(Role role) { return this.roles.get(role); }

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

	public GenderType getGender() { return gender; }

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public void addUserConversations(ConversationUser conversationUser){
		this.userConversations.add(conversationUser);
	}

	public List<ConversationUser> getUserConversations() {
		return userConversations;
	}

	public void setUserConversations(List<ConversationUser> userConversations) {
		this.userConversations = userConversations;
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

	public void addRole(Role roleType, RoleClass role){ roles.put(roleType, role); }

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	/**
	 * this function relate 2 users with family relationship. It updates both relationship lists.
	 * @param user2 other user to set the relationship.
	 *                 	If this.role == STUDENT then user2.role needs to be Responsible
	 *              	else if this.role == Responsible then user2.role needs to be Student
	 * @return 0 if correct, -1 if incorrect
	 *
	 * In order to save this into the db, at least one of the users has to be saved or updated.
	 */
	public int setParentalRelationship(User user2){
		RoleStudent roleStudent = null;
		RoleResponsible roleResponsible = null;
		if(this.roles.containsKey(Role.STUDENT) && user2.getRoles().containsKey(Role.RESPONSIBLE)){
			roleStudent = (RoleStudent) this.roles.get(Role.STUDENT);
			roleResponsible = (RoleResponsible) user2.getRoles().get(Role.RESPONSIBLE);
		}else if(this.roles.containsKey(Role.RESPONSIBLE) && user2.getRoles().containsKey(Role.STUDENT)){
			roleStudent = (RoleStudent) user2.getRoles().get(Role.STUDENT);
			roleResponsible = (RoleResponsible) this.roles.get(Role.RESPONSIBLE);
		}else
			return -1;
		roleStudent.addResponsible(roleResponsible);
		roleResponsible.addChild(roleStudent);


		return 0;
	}

	/**
	 * This function relates an Student with a group. this.student MUST be an student or this function will not work
	 *
	 * @param group to relate with the student.
	 *
	 * @return 0 if correct, -1 if incorrect
	 *
	 * In order to save this into the the student must be updated.
	 */
	public int setGroup(ClassGroup group){
		if(this.roles.containsKey(Role.STUDENT)){
			RoleStudent roleStudent = (RoleStudent) this.roles.get(Role.STUDENT);
			roleStudent.setGroup(group);
		}else
			return -1;

		return 0;
	}

	/**
	 * This function relates a Teacher with a group. this.teacher MUST be an teacher or this function will not work
	 * It updates both relationship lists.
	 * @param subject to relate with the teacher.
	 *
	 * @return 0 if correct, -1 if incorrect
	 *
	 * In order to save this into the the student must be updated.
	 */
	public int setSubject(Subject subject){
		if(this.roles.containsKey(Role.TEACHER)){
			RoleTeacher roleTeacher = (RoleTeacher) this.roles.get(Role.TEACHER);
			roleTeacher.addSubject(subject);
			subject.addTeacher(roleTeacher);
		}else
			return -1;

		return 0;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return userId.equals(user.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
}
