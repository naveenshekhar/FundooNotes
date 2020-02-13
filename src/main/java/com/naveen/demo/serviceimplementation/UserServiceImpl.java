package com.naveen.demo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.naveen.demo.dto.UserRegDto;
import com.naveen.demo.model.User;
import com.naveen.demo.repository.UserRepository;
import com.naveen.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	UserRegDto userRegDto = new UserRegDto();

	@Autowired
	UserRepository userRepository;

	
	User user=new User();

//	@Override
//	public void adduser(String firstName, String lastName, String email, String password) {

	@Override
	public void adduser(@RequestBody UserRegDto userReg) {
		
		String fname = userReg.getFirstName();
		user.setFirstName(fname);

		String lname = userReg.getLastName();
		user.setLastName(lname);

		String eMail = userReg.getEmail();
		user.setEmail(eMail);

		String passWord = userReg.getPassword();
		user.setPassword(passWord);
		// String lName=user.setLastName(lastName);

//		String fName = userRegDto.getFirstName();
//		String lName = userRegDto.getLastName();
//		String eMail = userRegDto.getEmail();
//		String passWord = userRegDto.getPassword();

		userRepository.registerUser(user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword());

	}

}
