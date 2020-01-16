package com.bridgelabz.fundoo.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.dto.ForgetPaswordDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.dto.UserLoginDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.UserService;
import com.bridgelabz.fundoo.utility.JwtGenerator;
import com.bridgelabz.fundoo.utility.SpringMail;

@Service
public class UserServiceImplementation implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

	@Autowired
	User user;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtGenerator tokenGenerator;
	@Autowired
	private SpringMail mail;

	@Override
	public User register(UserDto userdto) {

		user.setFirst_name(userdto.getFirst_name());
		user.setLast_name(userdto.getLast_name());
		user.setPhone_number(userdto.getPhone_number());
		user.setEmail(userdto.getEmail());
		user.setPassword(bcrypt.encode(userdto.getPassword()));
		userRepository.save(user);

		User isUserAvailableTwo = userRepository.FindByEmail(userdto.getEmail());
		String email = user.getEmail();
		String response = "http://localhost:8080/verify/" + tokenGenerator.jwtToken(isUserAvailableTwo.getId());
		mail.sendMail(email, response);
//		userRepository.insertData(user.getFirst_name(), user.getLast_name(), user.getPhone_number(), user.getEmail(),
//				bcrypt.encode(user.getPassword()));
		return user;

	}

	@Override
	public User login(UserLoginDto userLogin) {

		String emailFromDto = userLogin.getEmail();
		User user = userRepository.checkByEmail(userLogin.getEmail());
		String emailFromDB = user.getEmail();
		String passwordFrmDb = user.getPassword();
		String pswrdfrmDTO = userLogin.getPassword();

		boolean is_pswrd_matched = BCrypt.checkpw(passwordFrmDb, pswrdfrmDTO);

		if (emailFromDB.equals(emailFromDto)) {
			if (is_pswrd_matched) {
				System.out.println(user);
				return user;
			}
		} else
			return null;
		return null;
	}

	@Override
	public User forgetPassword(ForgetPaswordDto password, String email) {

		// tokenGenerator
//		User isUserAvailable = userRepository.FindByEmail(email);

//		User detailFrmDb = userRepository.checkByEmail(password.getEmail());
//		String userEmail = detailFrmDb.getEmail();
//		if (password.getPassword().equals(password.getConfirm_pass())) {
//
//			String responce = "http://localhost:8080/updatePassword"
//					+ JwtGenerator
//			return user;
//		}

		return null;
	}

	public boolean verify(String token) {
		try {
			logger.info("id in verification", tokenGenerator.parse(token));
			long id = tokenGenerator.parse(token);
			User isIdVerified = userRepository.findById(id);

			if (!isIdVerified.isVerified()) {
				System.out.println("save details");
				return true;
			} else {

				return false;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
