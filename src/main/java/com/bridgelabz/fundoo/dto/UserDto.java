package com.bridgelabz.fundoo.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserDto {

	private String first_name;

	private String last_name;

	private String phone_number;

	private String email;

	private String password;

}
