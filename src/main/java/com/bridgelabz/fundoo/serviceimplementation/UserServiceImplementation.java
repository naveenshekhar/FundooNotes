package com.bridgelabz.fundoo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.dto.ForgetPaswordDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.dto.UserLoginDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	User user = new User();
	@Autowired
	BCryptPasswordEncoder bcrypt;
	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(UserDto userdto) {
		user.setFirst_name(userdto.getFirst_name());

		user.setLast_name(userdto.getLast_name());

		user.setPhone_number(userdto.getPhone_number());

		user.setEmail(userdto.getEmail());

		user.setPassword(bcrypt.encode(userdto.getPassword()));

		userRepository.insertData(user.getFirst_name(), user.getLast_name(), user.getPhone_number(), user.getEmail(),
				bcrypt.encode(user.getPassword()));
		return user;

	}

	@Override
	public User login(UserLoginDto userLogin) {

		String emailFromDto = userLogin.getEmail();
		User user = userRepository.checkByEmail(userLogin.getEmail());
		String emailFromDB = user.getEmail();
		String passwordFrmDb = user.getPassword();
		String pswrdfrmDTO = userLogin.getPassword();
		if (emailFromDB.equals(emailFromDto)) {
			if (passwordFrmDb.equals(pswrdfrmDTO)) {
				System.out.println(user);
				return user;
			}
		} else
			return null;
		return null;
	}

	@Override
	public User forgerPassword(ForgetPaswordDto password) {

		User detailFrmDb = userRepository.checkByEmail(password.getEmail());
		String userEmail = detailFrmDb.getEmail();
		if (password.getPassword().equals(password.getConfirm_pass())) {
			userRepository.changepassword(password.getPassword(), userEmail);
			return user;
		}
		return null;
	}

}
