package com.bridgelabz.fundoo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationContext {

//	@Bean
//	public UserServiceImplementation getUserSevice()
//	{
//		return new UserServiceImplementation();
//	}
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
