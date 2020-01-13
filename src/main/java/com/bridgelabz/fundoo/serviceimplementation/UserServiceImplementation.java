package com.bridgelabz.fundoo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.ForgetPaswordDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.dto.UserLoginDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	
	User user=new User();
	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(UserDto userdto) {
		user.setFirst_name(userdto.getFirst_name());
		
		user.setLast_name(userdto.getLast_name());

		user.setPhone_number(userdto.getPhone_number());

		user.setEmail(userdto.getEmail());

		user.setPassword(userdto.getPassword());

		userRepository.insertData(user.getFirst_name(),user.getLast_name(),user.getPhone_number(),
				user.getEmail(),user.getPassword());
		return user;
		
	}

	@Override
	public User login(UserLoginDto userLogin) {
		
		String emailFromDB=user.getEmail();
		String emailFromDto=userLogin.getEmail();
		User user=userRepository.checkByEmail(userLogin.getEmail());
		
		if(emailFromDB.equals(emailFromDto))
		{
			return user;
		}
		else
			return null;
	}

	@Override
	public User forgerPassword(ForgetPaswordDto forgetpass) {

		String emailFromDB=user.getEmail();
		String emailFromDto=forgetpass.getEmail();
		
		if(emailFromDB.equals(emailFromDto))
		{
			return user;
		}
		return null;
	}

}
