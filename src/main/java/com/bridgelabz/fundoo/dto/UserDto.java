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
	private String firstName;
	
	@NotNull
	@Size(max = 50, message = "invalid name")
	private String lastName;
	
	@NotNull
	@Size(max = 12, message = "invalid phone number")
	private long pnoneNumber;
	
	@NotNull
	private String email;
	
	@NotNull
	@Size(max = 12, message = "invalid password")
	private String password;

}
