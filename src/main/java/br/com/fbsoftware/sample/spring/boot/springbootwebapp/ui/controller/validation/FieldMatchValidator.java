package br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Based on the code from <a href="https://memorynotfound.com/field-matching-bean-validation-annotation-example/">this article</a>
 *
 * @author Fabio M. Blanco
 * @since 12/04/2021
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private String firstFieldName;
	private String secondFieldName;
	private String message;

	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

			valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
		} catch (final Exception ignore) {
			// ignore
		}

		if (!valid) {
			context.buildConstraintViolationWithTemplate(message)
				   .addPropertyNode(firstFieldName)
				   .addConstraintViolation()
				   .disableDefaultConstraintViolation();
		}

		return valid;
	}
}
