package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.dto.UserLoginDto;
import com.bridgelabz.fundoo.model.User;

public interface UserService {

	User register(UserDto userdto);

	boolean login(UserLoginDto userLogin);

}
