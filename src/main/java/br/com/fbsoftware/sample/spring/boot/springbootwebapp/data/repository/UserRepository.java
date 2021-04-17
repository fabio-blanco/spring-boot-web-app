package br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.repository;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * User Spring Jpa repository
 *
 * @author Fabio Blanco
 * @since 09/04/2021
 */
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.login = :login")
	User getUserByLogin(@Param("login") String login);

	@Query("SELECT count(u) FROM User u WHERE u.email = :email")
	int countUsersByEmail(@Param("email") String email);

}
