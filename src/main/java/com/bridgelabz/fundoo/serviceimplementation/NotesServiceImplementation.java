package com.bridgelabz.fundoo.serviceimplementation;



import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

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

	@Autowired
	private JwtGenerator tokenGenerator;


	@Override
	public Notes create(NotesDto noteDto, String token) {
		try {
			Long parseToken = tokenGenerator.parse(token);
			User user = users.findbyId(parseToken);
			if (user != null) {
				Notes notes = new Notes();
				notes.setTitle(noteDto.getTitle());
				notes.setDescription(noteDto.getDescription());
//				notes.setColor("red");
//				notes.setArchived(false);
//				notes.setCreationTime(Timestamp.from(null));
				
				notesrepo.insertData(notes.getTitle(), notes.getDescription(), notes.getUserId(), notes.getReminder(),
						notes.getColor());
				return notes;
			}
		} catch (Exception e) {
         System.out.println(e);
         
		}
		return null;
	}

	@Override
	public Notes delete(NotesDto noteDto) {
		return notes;

	}

	@Override
	public Notes update(NotesDto noteDto) {
		return notes;

	}

}
