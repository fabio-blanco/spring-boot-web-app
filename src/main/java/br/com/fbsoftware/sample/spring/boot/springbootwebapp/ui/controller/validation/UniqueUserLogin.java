package br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Fabio M. Blanco
 * @since 16/04/2021
 */
@Documented
@Constraint(validatedBy = UniqueUserLoginValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserLogin {
	String message() default "{login.already.exists}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
