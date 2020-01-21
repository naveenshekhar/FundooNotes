package com.bridgelabz.fundoo.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.NotesDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.Notes;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.NotesRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.NotesService;
import com.bridgelabz.fundoo.utility.JwtGenerator;

@Service
public class NotesServiceImplementation implements NotesService {

	Notes notes;
	@Autowired
	private NotesRepository notesrepo;

	@Autowired
	private UserRepository users;

	@Autowired
	private UserDto userdto;

//	@Autowired
//	private JwtGenerator tokenGenerator;

	@Override
	public Notes create(NotesDto noteDto) {

	//	String token = tokenGenerator.parse(token);
		User user = users.FindByEmail(userdto.getEmail());

		if (user != null) {
			Notes notes = new Notes();
			notes.setTitle(noteDto.getTitle());
			notes.setDescription(noteDto.getDescription());
			notesrepo.insertData(notes.getTitle(), notes.getDescription(), notes.getUserId(), notes.getReminder(),
					notes.getColor());
			return notes;
		}
		return null;
	}

}
