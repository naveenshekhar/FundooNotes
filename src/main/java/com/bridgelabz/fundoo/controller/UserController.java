package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.service.UserService;

@RestController
public class UserController 
{
	@Autowired
	private UserService userService;
	
	public ResponseEntity<?> register(@RequestBody UserDto userDto)
	{
		return null;
		
	}
	
	

}
