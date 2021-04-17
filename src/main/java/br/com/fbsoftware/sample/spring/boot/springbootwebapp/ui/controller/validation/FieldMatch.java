package br.com.fbsoftware.sample.spring.boot.springbootwebapp.ui.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Based on the code from <a href="https://memorynotfound.com/field-matching-bean-validation-annotation-example/">this article</a>
 *
 * @author Fabio Blanco
 * @since 12/04/2021
 */

@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
@Target({TYPE, ANNOTATION_TYPE})
public @interface FieldMatch {
	String message() default "The fields must match";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String first();
	String second();

	@Target({TYPE, ANNOTATION_TYPE})
	@Retention(RUNTIME)
	@Documented
	@interface List {
		FieldMatch[] value();
	}
}
