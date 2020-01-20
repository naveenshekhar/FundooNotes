package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.ForgetPaswordDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.dto.UserLoginDto;
import com.bridgelabz.fundoo.model.User;

public interface UserService {

	User register(UserDto userdto);

	User login(UserLoginDto userLogin);

	boolean verify(String token);


	User forgetPassword(ForgetPaswordDto password);

}
