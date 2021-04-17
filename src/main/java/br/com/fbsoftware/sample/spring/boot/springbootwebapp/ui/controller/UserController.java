package br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.User;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.service.EmailAlreadyExistCheckedException;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.service.UserService;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * User Web Controller
 *
 * @author Fabio Blanco
 * @since 09/04/2021
 */
@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService service;

	public UserController(UserService userService) {
		this.service = userService;
	}

	@GetMapping
	public ModelAndView list() {

		List<User> users = service.findAll();

		return new ModelAndView("user-list", "users", users);

	}

	@GetMapping("new-user")
	public String displayNewUserForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "new-user";
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest req, Model model) {
		if(e.getMessage() != null && e.getMessage().contains("PUBLIC.USER(LOGIN)")) {
			// Just a plus because the @UniqueUserLogin validation may do this validation in a much cleaner way
			UserForm userForm = new UserForm();
			BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(userForm, "userForm");

			bindingResult.rejectValue("login", "login.already.exists");

			model.addAttribute("userForm", userForm);
			model.addAttribute("org.springframework.validation.BindingResult.userForm", bindingResult);
			return "new-user";
		}

		logger.error("Unexpected Violation Exception", e);

		model.addAttribute("exception", e)
			 .addAttribute("url", req.getRequestURL());

		return "error";
	}

	@PostMapping("new-user")
	public String addUser(@Valid UserForm userForm, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "new-user";
		} else {

			try {
				service.addUser(userForm);
			} catch (EmailAlreadyExistCheckedException e) {
				result.rejectValue("email", "email.already.exists");
				return "new-user";
			}
		}

		return "redirect:/users";
	}

	@GetMapping("first-use")
	public String firstUse(Model model, RedirectAttributes redirectAttributes) {
		if (service.count() > 0) {
			redirectAttributes.addAttribute("globalError",
											"First use User creation is only allowed for new databases! Please log as an Admin user for adding another user.");
			return "redirect:/";
		}

		model.addAttribute("firstUse", true);
		model.addAttribute("userForm", new UserForm());

		return "new-user";
	}

	@PostMapping("first-user")
	public String addFirstUser(@Valid UserForm userForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if (service.count() > 0) {
			redirectAttributes.addAttribute("globalError",
											"First use User creation is only allowed for new databases! Please log as an Admin user for adding another user.");
			return "redirect:/";
		}

		return addUser(userForm, result, null);
	}
}
