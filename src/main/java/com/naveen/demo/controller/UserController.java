package com.naveen.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.demo.dto.UserRegDto;
import com.naveen.demo.service.UserService;

@RestController
public class UserController {

	UserRegDto userReg = new UserRegDto();

	@Autowired
	UserService userService;

	@PostMapping("/user/register")
	public void addUser(@RequestBody UserRegDto userRegDto) {

		String firstName = userReg.getFirstName();
		String lastName = userReg.getLastName();
		String email = userReg.getEmail();
		String password = userReg.getPassword();

		userService.adduser(userRegDto);
	}

}
