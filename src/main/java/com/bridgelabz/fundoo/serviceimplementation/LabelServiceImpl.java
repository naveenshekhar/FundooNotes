package com.bridgelabz.fundoo.serviceimplementation;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoo.dto.LabelDto;
import com.bridgelabz.fundoo.model.Labels;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.LabelRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.LabelService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@Service
public class LabelServiceImpl implements LabelService {

	Labels label;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private JwtGenerator tokenGenerator;

	@Override
	public Labels create(LabelDto labelDto, String token) {

		try {
			Long parseToken = tokenGenerator.parse(token);
			User user = userRepo.findbyId(parseToken);

			if (user != null) {
				Labels l = new Labels();
				l.setLabelName(labelDto.getLableName());
				l.setLabelUser(user);
				labelRepository.createLabel(l.getLabelName(), l.getLabelUser());
				return label;
			}

		} catch (JWTVerificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
