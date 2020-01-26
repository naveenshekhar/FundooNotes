package com.bridgelabz.fundoo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.CollaboratorDto;
import com.bridgelabz.fundoo.model.Collaborator;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.CollaboratorRepository;
import com.bridgelabz.fundoo.repository.NotesRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.CollaboratorService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@Service
public class CollaboratorServiceImplementation implements CollaboratorService{
	
	@Autowired
	private JwtGenerator jwtGenerator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotesRepository noteRepository;

	@Autowired
	private CollaboratorRepository collaboratorRepository;

	@Override
	public Collaborator addCollaborator(CollaboratorDto collaboratorDto, String token, long noteId) {
		try {
			Collaborator collaborator = new Collaborator();
			Notes note = noteRepository.findbyId(noteId);
			Collaborator collaboratorDB = collaboratorRepository.findOneByEmail(collaboratorDto.getEmail(), noteId);
			if (note != null && collaboratorDB == null) {
//				collaborator.setEmail(collaboratorDB.getEmail());
				BeanUtils.copyProperties(collaboratorDto, collaborator);
				collaborator.setNoteId(note);
				collaboratorRepository.addCollaborator(collaborator.getNoteId(), collaborator.getEmail(), noteId);
				return collaborator;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Collaborator deleteCollaborator(long collaboratorId, String token, long noteId) {
		try {
			long userId = jwtGenerator.parse(token);
			Optional<User> user = userRepository.findById(userId);
			if (user != null) {
				Optional<Notes> note = noteRepository.findById(noteId);
				if (note != null) {
					Collaborator collaborator = collaboratorRepository.findById(collaboratorId);
					if (collaborator != null) {
						collaboratorRepository.deleteCollaborator(collaboratorId, noteId);
						return collaborator;
					}
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Collaborator> getAllCollaborators(String token, long noteId) {
		try {
			long userId = jwtGenerator.parse(token);
			if (userId != 0) {
				List<Notes> note = noteRepository.searchAllNoteByNoteId(userId, noteId);
				if (note != null) {
					return collaboratorRepository.getAllNoteCollaborators(noteId);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


}
