package com.bridgelabz.fundoo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoo.utility.SpringMail;

@Configuration
public class SpringMailConfig {
	@Bean
	public SpringMail getSpringmail() {
		return new SpringMail();

	}
}
