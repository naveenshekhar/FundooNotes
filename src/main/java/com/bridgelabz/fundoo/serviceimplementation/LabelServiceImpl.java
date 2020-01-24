package com.bridgelabz.fundoo.serviceimplementation;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoo.dto.DeleteLabelDto;
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
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Labels delete(DeleteLabelDto deletelabelDto, String token) {
		try {
			Long parseToken = tokenGenerator.parse(token);
			User user = userRepo.findbyId(parseToken);

			if (user != null) {
				Labels l = new Labels();
				l.setLabelId(deletelabelDto.getLabelId());
				l.setLabelUser(user);
				labelRepository.deleteLabel(l.getLabelId());
				return label;
			}

		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Labels getAllLabels(String token) {
		
		try {
			Long parseToken = tokenGenerator.parse(token);
			User user = userRepo.findbyId(parseToken);
			System.out.println("id :"+user.getId());
			if (user != null) {
			List<Labels> list=labelRepository.getAllLabel(user.getId());
				return label;
			}

		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
