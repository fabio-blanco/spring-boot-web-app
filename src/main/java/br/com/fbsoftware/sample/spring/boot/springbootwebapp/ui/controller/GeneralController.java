package br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author iogui
 * @since 14/04/2021
 */
@Controller
public class GeneralController {

	private final UserRepository repository;

	public GeneralController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public String home(@ModelAttribute("globalError") String globalError) {
		if(repository.count() == 0) {
			return "redirect:/users/first-use";
		}

		return "index";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

}
