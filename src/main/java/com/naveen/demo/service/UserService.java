package com.naveen.demo.service;

import com.naveen.demo.dto.UserRegDto;

public interface UserService {

//	public void adduser(String firstName, String lastName, String email, String password);

	void adduser(UserRegDto userReg);

}
