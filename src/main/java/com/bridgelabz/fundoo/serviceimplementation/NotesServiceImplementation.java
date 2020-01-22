package com.bridgelabz.fundoo.serviceimplementation;



import java.sql.Timestamp;
import java.util.Date;

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
    Date date = new Date();
Long time = date.getTime();
Timestamp timeStamp = new Timestamp(time);

	@Override
	public Notes create(NotesDto noteDto, String token) {
		try {
			long parseToken = tokenGenerator.parse(token);
			User user = users.findbyId(parseToken);
			if (user != null) {
				Notes notes = new Notes();
				notes.setTitle(noteDto.getTitle());
				notes.setDescription(noteDto.getDescription());
				notes.setUser(user);
				notesrepo.insertData(notes.getColor(),notes.getTitle(),notes.getDescription(), notes.isPinned(),notes.isArchived(),timeStamp, parseToken);
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

	@Override
	public boolean pin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean trash(int noteId, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int noteId, String token) {
		// TODO Auto-generated method stub
		return false;
	}

}
