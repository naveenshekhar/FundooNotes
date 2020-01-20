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

	User user = new User();
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

		user.setFirstName(userdto.getFirstName());
		user.setLastName(userdto.getLastName());
		user.setPhoneNumber(userdto.getPhoneNumber());
		user.setEmail(userdto.getEmail());
		user.setPassword(bcrypt.encode(userdto.getPassword()));
		userRepository.save(user);
		User isUserAvailableTwo = userRepository.FindByEmail(userdto.getEmail());
		System.out.println("1");
		String email = user.getEmail();
		System.out.println(email);
		System.out.println(isUserAvailableTwo.getId());
		String response = "http://localhost:8080/verify/" + tokenGenerator.jwtToken(isUserAvailableTwo.getId());
		System.out.println("4" + response);
		mail.sendMail(email, response);
		userRepository.insertData(user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail(),
				bcrypt.encode(user.getPassword()));
		return user;

	}
//	@Override
//	public User verify(String token) {
//		try {
//			System.out.println("Inside");
//			loggger.info("Id Varification", (long) jwtGenerator.parse(token));
//			long id = jwtGenerator.parse(token);
//			System.out.println(token);
//			User isIdValied = userRepository.findById(id);
//			if (!isIdValied.isVerified()) {
//				userRepository.updateIsVarified(id);
//				System.out.println("save details");
//				return user;
//			} else {
//				System.out.println("already varified");
//				return user;
//			}
//		} catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	@Override
	public User login(UserLoginDto userLogin) {

		String emailFromDto = userLogin.getEmail();
		User user = userRepository.checkByEmail(userLogin.getEmail());
		String emailFromDB = user.getEmail();
		String passwordFrmDb = user.getPassword();
		String pswrdfrmDTO = userLogin.getPassword();

		boolean isPswrdMatched = BCrypt.checkpw(passwordFrmDb, pswrdfrmDTO);

		if (emailFromDB.equals(emailFromDto)) {
			if (isPswrdMatched) {
				System.out.println(user);
				return user;
			}
		} else
			return null;
		return null;
	}

	@Override
	public User forgetPassword(ForgetPaswordDto email) {

		// tokenGenerator
		User isUserAvailable = userRepository.FindByEmail(email);

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
		System.out.println("3");
		try {
			System.out.println("2");
			logger.info("id in verification", tokenGenerator.parse(token));
			System.out.println("t" + token);
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
