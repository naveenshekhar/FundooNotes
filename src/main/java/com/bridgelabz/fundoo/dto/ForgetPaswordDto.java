package com.bridgelabz.fundoo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPaswordDto {
	
	private String password;
	private String reEnterPassword;
}
