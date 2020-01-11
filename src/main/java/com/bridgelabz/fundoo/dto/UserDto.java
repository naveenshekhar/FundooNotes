package com.bridgelabz.fundoo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	@NotNull
	@Size(max = 50, message = "Invalid name")
	private String first_name;

	@NotNull
	@Size(max = 50, message = "invalid name")
	private String last_name;

	@NotNull
	@Size(max = 10, message = "invalid phone number")
	private String phone_number;

	@NotNull
	private String email;

	@NotNull
	@Size(max = 12, message = "invalid password")
	private String password;

}
