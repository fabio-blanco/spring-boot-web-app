package br.com.fbsoftware.sample.spring.boot.springbootwebapp.security;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.User;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author iogui
 * @since 14/04/2021
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userservice;

	public UserDetailsServiceImpl(UserService userservice) {
		this.userservice = userservice;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userservice.getUserByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new CustomUserDetails(user);
	}
}
