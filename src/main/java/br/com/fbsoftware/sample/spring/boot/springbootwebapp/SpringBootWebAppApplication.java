package br.com.fbsoftware.sample.spring.boot.springbootwebapp;

import br.com.fbsoftware.sample.spring.boot.springbootwebapp.data.model.UserRole;
import br.com.fbsoftware.sample.spring.boot.springbootwebapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class SpringBootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebAppApplication.class, args);
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public CommandLineRunner startupDataLoader(UserService userService) {
		return args -> {
			if (userService.count() == 0) {
				UserRole userRole = new UserRole();
				userRole.setName("User");
				UserRole adminRole = new UserRole();
				adminRole.setName("Admin");

				userService.save(userRole);
				userService.save(adminRole);
			}
		};
	}

}
