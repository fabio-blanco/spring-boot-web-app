package br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.validation;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author iogui
 * @since 16/04/2021
 */
public class UniqueUserLoginValidator implements ConstraintValidator<UniqueUserLogin, String> {

	private final UserService userService;

	public UniqueUserLoginValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void initialize(UniqueUserLogin constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = false;
		if (value != null && userService.getUserByLogin(value.trim()) == null) {
			valid = true;
		}

		return valid;
	}
}
