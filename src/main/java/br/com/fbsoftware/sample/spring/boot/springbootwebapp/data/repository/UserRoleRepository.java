package br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.repository;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Fabio M. Blanco
 * @since 14/04/2021
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Query("SELECT r FROM UserRole r WHERE r.name = :name")
	UserRole getByName(@Param("name") String name);
}
