package com.model;

import com.model.enums.Role;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@NamedQueries({
		@NamedQuery(name = "Authorities.getByEmail", query = "SELECT a FROM Authorities a WHERE a.emailId =:emailId"),
//             + "LEFT JOIN Tenant te ON te.room = r.id"
//             + "WHERE r.id = :id")

})
public class Authorities implements Serializable {

	private static final long serialVersionUID = 8734140534986494039L;

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String authorityId;
	private String emailId;
	private String authorities;

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Authorities() { }

	public Authorities(String emailId, String authorities) {
		this.emailId = emailId;
		this.authorities = authorities;
	}
}
