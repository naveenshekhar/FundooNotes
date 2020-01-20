package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.ForgetPaswordDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.dto.UserLoginDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<Responce> register(@RequestBody UserDto userDto) {

		User user = service.register(userDto);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Registration Successfully", 200, userDto));
		} else {
			System.out.println("hello else");
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Responce("User Already Exist", 400, userDto));

		}
	}

	@PostMapping("/login")
	public ResponseEntity<Responce> login(@RequestBody UserLoginDto userLogin) {

		User result = service.login(userLogin);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("Successful", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("UnSuccessful", 400));
		}
	}

	@PostMapping("/forgetpass")
	public ResponseEntity<Responce> forgetpass(@RequestBody ForgetPaswordDto password) {
		User result = service.forgetPassword(password);

		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("Successful", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("UnSuccessful", 400));
		}
	}

	@PostMapping("/verify/{token}")
	public ResponseEntity<Responce> verify(@PathVariable("token") String token)
	{
	 boolean result=service.verify(token);
	 if(result)
	 {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("Successful", 200,result));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("UnSuccessful", 400));
		}
	}
}
