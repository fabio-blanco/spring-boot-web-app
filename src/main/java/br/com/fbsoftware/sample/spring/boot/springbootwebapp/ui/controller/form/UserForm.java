package br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.form;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.validation.FieldMatch;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.validation.UniqueUserLogin;

import javax.validation.constraints.*;
import java.time.OffsetDateTime;

/**
 * @author Fabio M. Blanco
 * @since 12/04/2021
 */
@FieldMatch(first = "password", second = "retypePassword", message = "The password fields must match")
public class UserForm {

	@UniqueUserLogin
	@Size(min= 3, max=50)
	private String login;

	@Size(min = 5, max = 100)
	private String name;

	@NotBlank
	@Email
	private String email;

	@Size(min = 6, max = 16)
	private String password;

	@Size(min = 6, max = 16)
	private String retypePassword;

	private boolean roleAdmin;

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

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public boolean isRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(boolean roleAdmin) {
		this.roleAdmin = roleAdmin;
	}
}
