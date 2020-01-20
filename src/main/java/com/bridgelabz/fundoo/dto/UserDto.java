package com.bridgelabz.fundoo.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserDto {

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String email;

	private String password;

}
