package com.bridgelabz.fundoo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class ApplicationContext {

//	@Bean
//	public CollaboratorServiceImplementation getCollaboratorService()
//	{
//		return new CollaboratorServiceImplementation();
//	}
//	@Bean
//	public CollaboratorRepository collaboratorRepository()
//	{
//		return new CollaboratorRepository();
//	}
//	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
