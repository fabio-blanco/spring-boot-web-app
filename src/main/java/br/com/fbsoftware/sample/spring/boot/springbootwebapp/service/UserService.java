package br.com.fbsoftware.sample.spring.boot.springbootwebapp.service;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.User;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.UserRole;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.form.UserForm;

import java.util.List;

/**
 * @author iogui
 * @since 16/04/2021
 */
public interface UserService {
	List<User> findAll();

	long count();

	User addUser(UserForm userForm) throws EmailAlreadyExistCheckedException;

	User getUserByLogin(String login);

	UserRole save(UserRole userRole);
}
