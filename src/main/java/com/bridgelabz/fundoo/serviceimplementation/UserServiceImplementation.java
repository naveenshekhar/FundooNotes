package com.bridgelabz.fundoo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	
	User user=new User();
	@Autowired
	private UserRepository userRepository;

	@Override
	public void register(UserDto userdto) {
		user.setFirstName(userdto.getFirstName());
		
		user.setLastName(userdto.getLastName());

		user.setPhoneNumber(userdto.getPnoneNumber());

		user.setEmail(userdto.getEmail());

		user.setPassword(userdto.getPassword());

		userRepository.insertData(userdto.getFirstName(),userdto.getLastName(),userdto.getPnoneNumber(),
				userdto.getEmail(),userdto.getPassword());
	}

}
