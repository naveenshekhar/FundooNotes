package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoo.dto.ForgetPaswordDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.dto.UserLoginDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.responces.Responce;
import com.bridgelabz.fundoo.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register/")
	public ResponseEntity<Responce> register(@RequestBody UserDto userDto) {
		System.out.println("11111");
		User user = service.register(userDto);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Responce("Registered Successfully", 200, userDto));
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

	@PostMapping("/updatePassword/{token}")
	public ResponseEntity<Responce> updatePassword(@RequestBody ForgetPaswordDto password,
			@PathVariable("token") String token) throws JWTVerificationException, Exception {
		User result = service.updatePassword(password, token);

		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("Successful", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("UnSuccessful", 400));
		}
	}

	/*
	 * @PostMapping("/verify/{token}")
	 * 
	 * @ApiOperation(value = "Api to verify",response = Responce.class) public
	 * ResponseEntity<Responce> verify(@PathVariable("token") String token) {
	 * System.out.println("111"+token); User result = service.verifyUser(token);
	 * System.out.println(result); if (result!=null) { return
	 * ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("Successful",
	 * 200, result)); } else { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	 * Responce("UnSuccessful", 400)); } }
	 */
	@GetMapping("/verify/{token}")
	public ResponseEntity<Responce> verifyUser(@PathVariable("token") String token) {
		System.out.println("Token for verify " + token);
		User user = service.verifyUser(token);

		return (user) != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responce("Verified", 200))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce("Not verified", 400));
	}
}
