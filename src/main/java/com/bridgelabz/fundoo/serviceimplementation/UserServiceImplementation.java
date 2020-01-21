package com.bridgelabz.fundoo.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
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

	private User user = new User();
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
		User checkMail = userRepository.FindByEmail(userdto.getEmail());
		if (checkMail == null) {
			user.setFirstName(userdto.getFirstName());
			user.setLastName(userdto.getLastName());
			user.setPhoneNumber(userdto.getPhoneNumber());
			user.setEmail(userdto.getEmail());
			user.setPassword(bcrypt.encode(userdto.getPassword()));
			userRepository.save(user);
			User isUserAvailableTwo = userRepository.FindByEmail(userdto.getEmail());
			String email = user.getEmail();
			String response = "http://localhost:8080/verify/" + tokenGenerator.jwtToken(isUserAvailableTwo.getId());
			mail.sendMail(email, response);
			return user;
		}
		return null;
	}

	@Override
	public User login(UserLoginDto userLogin) {

		String emailFromDto = userLogin.getEmail();
		User user = userRepository.FindByEmail(userLogin.getEmail());
		String emailFromDB = user.getEmail();
		String passwordFrmDb = user.getPassword();
		String pswrdfrmDTO = userLogin.getPassword();
		if (emailFromDB.equals(emailFromDto)) {
			boolean isPswrdMatched = bcrypt.matches(passwordFrmDb, pswrdfrmDTO);
			if (isPswrdMatched) {
				System.out.println(user);
				return user;
			}
			return user;
		}
		return null;
	}

	@Override
	public User forgetPassword(String email) {

		User isUserAvailable = userRepository.FindByEmail(email);

		if (isUserAvailable != null) {
			if (isUserAvailable.isVerified()) {
				String response = "http://localhost:8080/update/" + tokenGenerator.jwtToken(user.getId());
				mail.sendMail(email, response);
				return user;
			}
		}
		return null;
	}

	public User updatePassword(ForgetPaswordDto forgetpass, String token)
			throws JWTVerificationException, Exception, Exception {
		if (forgetpass.getPassword().equals(forgetpass.getReEnterPassword())) {
			logger.info("id in verification", tokenGenerator.parse(token));
			long id = tokenGenerator.parse(token);
			User isIdVerified = userRepository.findById(id);
			if (isIdVerified.isVerified()) {
				isIdVerified.setPassword(forgetpass.getPassword());
				userRepository.changepassword(isIdVerified.getPassword(), id);
				return isIdVerified;
			}
		}
		return null;

	}

	public boolean verify(String token) {
		System.out.println("Hello");
		try {
			logger.info("id in verification", tokenGenerator.parse(token));
			System.out.println("inside verify method..");
			long id = tokenGenerator.parse(token);
			User isIdVerified = userRepository.findById(id);
			if (!isIdVerified.isVerified()) {
				userRepository.updateIsVarified(id);
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
