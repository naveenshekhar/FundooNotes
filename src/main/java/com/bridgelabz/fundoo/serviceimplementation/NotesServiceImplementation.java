package com.bridgelabz.fundoo.serviceimplementation;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoo.dto.NotesDto;
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
				notesrepo.insertData(notes.getColor(), notes.getTitle(), notes.getDescription(), notes.isPinned(),
						notes.isArchived(), timeStamp, parseToken);
				return notes;

			}
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}

	@Override
	public Notes update(long noteId,NotesDto notesDto, String token) {
		try {
			long ptoken = tokenGenerator.parse(token);
			User user = users.findbyId(ptoken);
			Notes note = notesrepo.findbyId(noteId);
			if (user != null) {
				if(note!=null)
				{
					System.out.println("11");
				notesrepo.update(note.getTitle(), note.getDescription(), noteId);
				return note;
				}
				return null;
			} else {
				return null;
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
	public boolean pin() {
		return false;
	}

	@Override
	public boolean trash(int noteId, String token) {
		return false;
	}

	@Override
	public boolean delete(int noteId, String token) {
		return false;
	}

	@Override
	public Notes delete(int id) {
		return null;
	}

}
