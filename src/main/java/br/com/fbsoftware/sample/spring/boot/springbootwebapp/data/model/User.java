package br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * User jpa entity
 * @author Fabio M. Blanco
 * @since 09/04/2021
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 5488179168112575967L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "login", length = 50, unique = true)
	private String login;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name="email", length = 200, unique = true)
	private String email;

	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "offset_date_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime offsetDateTime;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private UserRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public OffsetDateTime getOffsetDateTime() {
		return offsetDateTime;
	}

	public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
		this.offsetDateTime = offsetDateTime;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
