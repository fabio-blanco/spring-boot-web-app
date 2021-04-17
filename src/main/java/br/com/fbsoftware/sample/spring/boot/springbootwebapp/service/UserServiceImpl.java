package br.com.fbsoftware.sample.spring.boot.springbootwebapp.service;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.User;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.UserRole;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.repository.UserRepository;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.repository.UserRoleRepository;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.form.UserForm;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Fabio M. Blanco
 * @since 16/04/2021
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;

	private final UserRoleRepository userRoleRepository;

	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository repository,
						   UserRoleRepository userRoleRepository,
						   PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> findAll() {
		return repository.findAll(Sort.by("name"));
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public User addUser(UserForm userForm) throws EmailAlreadyExistCheckedException {
		validateUniqueEmail(userForm.getEmail());

		User user = new User();
		user.setLogin(userForm.getLogin());
		user.setName(userForm.getName());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setEmail(userForm.getEmail().trim());
		user.setOffsetDateTime(OffsetDateTime.now());

		UserRole userRole;
		if(userForm.isRoleAdmin()) {
			userRole = userRoleRepository.getByName("Admin");
		} else {
			userRole = userRoleRepository.getByName("User");
		}
		user.setRole(userRole);

		return repository.save(user);
	}

	private void validateUniqueEmail(String email) throws EmailAlreadyExistCheckedException {
		if(repository.countUsersByEmail(email.trim()) > 0) {
			throw new EmailAlreadyExistCheckedException();
		}
	}

	@Override
	public User getUserByLogin(String login) {
		return repository.getUserByLogin(login);
	}

	@Override
	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

}
